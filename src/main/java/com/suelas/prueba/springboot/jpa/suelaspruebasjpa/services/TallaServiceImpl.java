package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.AttributeTallaException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TallaRepository;

@Service
public class TallaServiceImpl implements TallaService {

    @Autowired
    private TallaRepository tallaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Talla> findOneWhitProds(String name) {
        return tallaRepository.findOneWithProducts(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Talla> findAll() {
        return (List<Talla>) tallaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Talla> findById(Long id) {
        return tallaRepository.findById(id);
    }

    @Override
    @Transactional
    public Talla save(Talla talla) {
        return tallaRepository.save(talla);
    }

    @Override
    @Transactional
    public Optional<Talla> update(Talla talla, Long id) {
        Optional<Talla> opTall = this.findById(id);
        Talla tallaBd = null;
        if (opTall.isPresent()) {
            tallaBd = opTall.orElseThrow();
            tallaBd.setName(talla.getName());
        }
        return Optional.ofNullable(tallaBd);
    }

    @Override
    @Transactional
    public Optional<Talla> delete(Long id) {
        Optional<Talla> opTalla = tallaRepository.findById(id);
        opTalla.ifPresent(op -> {
            tallaRepository.delete(op);
        });
        return opTalla;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existByName(String name) {
        return tallaRepository.existsByName(name);
    }

    @Override
    public Optional<Talla> deleteAll(Long id) throws AttributeTallaException {
        Optional<Talla> opTalla = tallaRepository.findOneWithProductsById(id);
        List<Product> prod = new ArrayList<>();
        if (opTalla.isPresent()) {
            Talla tallaDb = opTalla.orElseThrow();
            if (!tallaDb.getProducts().isEmpty()) {

                tallaDb.getProducts().forEach(p -> {
                    prod.add(p);
                });
                prod.forEach(p -> {
                    boolean isEmpty = tallaDb.removeProducts(p);
                    if (isEmpty) {

                        throw new AttributeTallaException(
                                "Atencion! Podria estar dejando sin ninguna talla algun producto, se recomienda primero eliminar producto asociado");

                    } else {
                        tallaRepository.save(tallaDb);
                    }
                });
            }
            // tallaRepository.delete(tallaDb);
            return this.delete(id);
        }
        return Optional.ofNullable(opTalla.get());
    }

}
