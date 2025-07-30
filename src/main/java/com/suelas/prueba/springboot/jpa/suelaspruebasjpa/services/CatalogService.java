package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.DetailsDto;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Color;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tamanio;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tipo;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.ColorRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.ProductRespository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TallaRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TamanioRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TipoRepository;

@Service
public class CatalogService {

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private TamanioRepository tamanioRepository;

    @Autowired
    private ProductRespository productRespository;

    @SuppressWarnings("null")
    @Transactional(readOnly = true)
    public List<Product> listTallaDetail(DetailsDto details) {
        List<Long> ids = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        if (!details.getTalla().isBlank()) {
            Talla talla = null;
            Optional<Talla> opTalla = tallaRepository.findOneWithProducts(details.getTalla());

            if (opTalla.isPresent()) {
                talla = opTalla.orElseThrow();
            }
            ids = talla.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
            if (!ids.isEmpty()) {
                productList = productRespository.findProductsByIds(ids);
            } else { //*AQUI */
                return new ArrayList<>();
            }

            if (!details.getColor().isBlank()) {
                ids = idsListColors(productList, details.getColor(), ids);
                if (!ids.isEmpty()) {
                    productList = productRespository.findProductsByIds(ids);
                } else {
                    return new ArrayList<>();
                }
            }

            if (!details.getTipo().isBlank()) {
                ids = idsListTipo(productList, details.getTipo(), ids);
                if (!ids.isEmpty()) {
                    productList = productRespository.findProductsByIds(ids);
                } else {
                    return new ArrayList<>();
                }
            }

            if (!details.getTamanio().isBlank()) {
                ids = idsListTamanio(productList, details.getTamanio(), ids);
                if (!ids.isEmpty()) {
                    productList = productRespository.findProductsByIds(ids);
                } else {
                    return new ArrayList<>();
                }
            }
            System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
            return productList;
        } 
        //FIN DEL PRIMER FILTRO DE BUSQUEDA--------------------------------------------->

        else if (!details.getColor().isBlank()) {
            Color color = null;
            Optional<Color> opColor = colorRepository.findOneWithProducts(details.getColor());
            if (opColor.isPresent()) {
                color = opColor.orElseThrow();
            }
            ids = color.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
            if (!ids.isEmpty()) {
                productList = productRespository.findProductsByIds(ids);
                System.out.println("----------------------> " + ids);
            } else {
                return new ArrayList<>();
            }

            if (!details.getTipo().isBlank()) {
                ids = idsListTipo(productList, details.getTipo(), ids);
                if (!ids.isEmpty()) {
                    productList = productRespository.findProductsByIds(ids);
                } else {
                    return new ArrayList<>();
                }
            }

            if (!details.getTamanio().isBlank()) {
                ids = idsListTamanio(productList, details.getTamanio(), ids);
                if (!ids.isEmpty()) {
                    productList = productRespository.findProductsByIds(ids);
                } else {
                    return new ArrayList<>();
                }

            }
            System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
            return productList;
        }
        //FIN DEL SEGUNDO FILTRO DE BUSQUEDA------------------------------------------->

        else if (!details.getTipo().isBlank()) {
            Tipo tipo = null;
            Optional<Tipo> opTipo = tipoRepository.findOneWithProducts(details.getTipo());
            if (opTipo.isPresent()) {
                tipo = opTipo.orElseThrow();
            }
            ids = tipo.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
            if (!ids.isEmpty()) {
                productList = productRespository.findProductsByIds(ids);
            } else {
                return new ArrayList<>();
            }

            if (!details.getTamanio().isBlank()) {
                ids = idsListTamanio(productList, details.getTamanio(), ids);
                if (!ids.isEmpty()) {
                    productList = productRespository.findProductsByIds(ids);
                } else {
                    return new ArrayList<>();
                }
            }
            System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
            return productList;
        }
        //FIN DEL TERCER FILTRO DE BUSQUEDA----------------------------------------------->
        
        else if (!details.getTamanio().isBlank()) {
            Tamanio tamanio = null;
            Optional<Tamanio> opTamanio = tamanioRepository.findOneWithProducts(details.getTamanio());
            if (opTamanio.isPresent()) {
                tamanio = opTamanio.orElseThrow();
            }
            ids = tamanio.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
            if (!ids.isEmpty()) {
                productList = productRespository.findProductsByIds(ids);
            } else {
                return new ArrayList<>();
            }

            System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
            return productList;
        }
        //FIN DEL CUARTO FILTRO DE BUSQUEDA------------------------------------------------>
        return new ArrayList<>();
    }
    //FIN DEL METODO DE BUSQUEDA POR FILTRADO----------------------------------------------------->

    public List<Long> idsListTipo(List<Product> productList, String detail, List<Long> ids) {

        for (Product product : productList) {
            if (!product.getTipo().getName().equals(detail)) {
                ids.remove(product.getId());
            }
        }
        return ids;
    }

    public List<Long> idsListTamanio(List<Product> productList, String detail, List<Long> ids) {

        for (Product product : productList) {
            if (!product.getTamanio().getName().equals(detail)) {
                ids.remove(product.getId());
            }
        }
        return ids;
    }

    public List<Long> idsListColors(List<Product> productList, String detail, List<Long> ids) {
        for (Product product : productList) {
            if (!product.getColor().getName().equals(detail)) {
                ids.remove(product.getId());
            }
        }
        return ids;
    }

    public List<Long> idsListTallas(List<Product> productList, String details, List<Long> ids) {
        for (Product product : productList) {
            boolean flag = false;
            for (Talla talla : product.getTallas()) {
                if (talla.getName().contains(details)) {
                    flag = true;
                }
            }
            if (flag == false) {
                ids.remove(product.getId());
            }
        }
        return ids;
    }

}
