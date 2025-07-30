package com.suelas.prueba.springboot.jpa.suelaspruebasjpa;

//import java.util.ArrayList;

//import java.util.List;

//import java.util.stream.Collectors;
//import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.DetailsDto;

//import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.ProductRespository;

@SpringBootApplication
public class SuelasPruebasJpaApplication implements CommandLineRunner {

    /*@Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private TamanioRepository tamanioRepository;

    @Autowired
    private ProductService prodService;*/

    public static void main(String[] args) {

        SpringApplication.run(SuelasPruebasJpaApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        DetailsDto details = new DetailsDto();
        details.setTipo("perforada");
        details.setTamanio("mujer");

        //listTallaDetail(details);

    }

    /*@SuppressWarnings({ "null" })
    public List<Product> listTallaDetail(DetailsDto details) {
        List<Long> ids = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        if (details.getTalla() != null) {
            Talla talla = null;
            Optional<Talla> opTalla = tallaRepository.findOneWithProducts(details.getTalla());
            if (opTalla.isPresent()) {
                talla = opTalla.orElseThrow();
            }
            ids = talla.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());

            System.out.println("FIRST LIST OF:-------->" + ids);
            if (!ids.isEmpty()) {
                productList = prodService.findByIds(ids);
            } else {
                return null;
            }

            if (details.getColor() != null) {
                ids = idsListColors(productList, details.getColor(), ids);
                System.out.println("SECOND LIST OF:---------------->" + ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }

            if (details.getTipo() != null) {
                ids = idsListTipo(productList, details.getTipo(), ids);
                System.out.println("THIRD LIST OF:--------------->" + ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }

            if (details.getTamanio() != null) {
                ids = idsListTamanio(productList, details.getTamanio(), ids);
                System.out.println("FOURTH LIST OF:--------------->" + ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }
            System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
            return productList;

        } else if (details.getColor() != null) {
            Color color = null;
            Optional<Color> opColor = colorRepository.findOneWithProducts(details.getColor());
            if (opColor.isPresent()) {
                color = opColor.orElseThrow();
            }
            ids = color.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
            if (!ids.isEmpty()) {
                productList = prodService.findByIds(ids);
            } else {
                return null;
            }

            /*if (details.getTalla() != null) {
                ids = idsListTallas(productList, details.getTalla(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }

            if (details.getTipo() != null) {
                ids = idsListTipo(productList, details.getTipo(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }

            if (details.getTamanio() != null) {
                ids = idsListTamanio(productList, details.getTamanio(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
                System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
                return productList;
            }
        } else if (details.getTipo() != null) {
            Tipo tipo = null;
            Optional<Tipo> opTipo = tipoRepository.findOneWithProducts(details.getTipo());
            if (opTipo.isPresent()) {
                tipo = opTipo.orElseThrow();
            }
            ids = tipo.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
            if (!ids.isEmpty()) {
                productList = prodService.findByIds(ids);
            } else {
                return null;
            }
            /*if (details.getColor() != null) {
                ids = idsListColors(productList, details.getColor(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }
            if (details.getTalla() != null) {
                ids = idsListTallas(productList, details.getTalla(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }
            if (details.getTamanio() != null) {
                ids = idsListTamanio(productList, details.getTamanio(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
                System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
                return productList;
            }
        } else if (details.getTamanio() != null) {
            Tamanio tamanio = null;
            Optional<Tamanio> opTamanio = tamanioRepository.findOneWithProducts(details.getTamanio());
            if (opTamanio.isPresent()) {
                tamanio = opTamanio.orElseThrow();
            }
            ids = tamanio.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
            if (!ids.isEmpty()) {
                productList = prodService.findByIds(ids);
            } else {
                return null;
            }
            /*if (details.getColor() != null) {
                ids = idsListColors(productList, details.getColor(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }
            if (details.getTalla() != null) {
                ids = idsListTallas(productList, details.getTalla(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }
            if (details.getTipo() != null) {
                ids = idsListTipo(productList, details.getTipo(), ids);
                if (!ids.isEmpty()) {
                    productList = prodService.findByIds(ids);
                } else {
                    return null;
                }
            }
            System.out.println("LISTA DE IDS FILTRADOS------------>" + ids);
            return productList;
        }
        return null;
    }

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

    public List<Long> idsListColors(List<Product> productList, String details, List<Long> ids) {
        for (Product p : productList) {
            boolean flag = false;
            for (Color c : p.getColores()) {
                if (c.getName().contains(details)) {
                    flag = true;
                }
            }
            if (flag == false) {
                ids.remove(p.getId());
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
    }*/

}
