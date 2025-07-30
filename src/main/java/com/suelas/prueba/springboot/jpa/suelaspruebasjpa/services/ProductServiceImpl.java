package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.FileReadException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.FileWriteException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Cantidad;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Color;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Images;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tamanio;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tipo;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.ProductRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.ColorRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.ProductRespository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TallaRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TamanioRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TipoRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRespository prodRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private TamanioRepository tamanioRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ImagesService imagesService;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) prodRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return prodRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return prodRepository.findById(id);
    }

    @Override
    @Transactional
    public Product onlySave(Product product) {
        return prodRepository.save(product);
    }

    @Override
    @Transactional
    public Product save(ProductRequest product) throws FileReadException, FileWriteException {
        List<Talla> tallasProduct = new ArrayList<>(); // creamos una lista del tipo Talla entidad
        List<Cantidad> cantidadProduct = new ArrayList<>();

        Product prodDB = new Product();
        prodDB.setName(product.getName());
        prodDB.setPrecio(product.getPrecio());
        
        prodDB.setRebaja(product.isRebaja());
        prodDB.setDisponible(product.isDisponible());

        saveImg(product).forEach(f -> {
            prodDB.getImages().add(f);
        });

        product.getListCantidad().forEach(c -> {
            System.out.println("CANTIDADES******################################@@@@@@@@@@@@@@@@@@@@@@@@" + c);
        });
        product.getTallaList().forEach(p ->{
            System.out.println("TALLAS------------------->" + p);
        });
        product.getListCantidadDto().forEach(c -> {
            Optional<Talla> opTalla = tallaRepository.findByName(c.getName());
            opTalla.ifPresent(t -> {
                Cantidad cantidad = new Cantidad();
                cantidad.setName(c.getName());
                cantidad.setTotal(c.getTotal());
                cantidadProduct.add(cantidad);
            });
            System.out.println("NOMBRE-----> " + c.getName() + "CANTIDAD-----> " + c.getTotal());
        });
        prodDB.setCantidad(cantidadProduct);
        /*product.getListCantidad().forEach(c -> {
            System.out.println("XXXXXXXXXXXXXXXXXX" + c);
        });*/
        /*product.getListCantidad().forEach(c -> {
            Cantidad cant = new Cantidad();
            cant.setName(c.getName());
            cant.setTotal(c.getTotal());
            cantidadProduct.add(cant);
        });

        cantidadProduct.forEach(c->{
            prodDB.getCantidades().add(c);
        });*/

        product.getTallaList().forEach(p -> {
            Optional<Talla> opTalla = tallaRepository.findByName(p);
            opTalla.ifPresent(t -> {
                tallasProduct.add(t);
            });
        });
        prodDB.setTallas(tallasProduct);

        Optional<Tipo> opTipo = tipoRepository.findByName(product.getTipo());
        opTipo.ifPresent(t -> {
            prodDB.setTipo(t);
        });

        Optional<Tamanio> opTamanio = tamanioRepository.findByName(product.getTamanio());
        opTamanio.ifPresent(t -> {
            prodDB.setTamanio(t);
        });

        Optional<Color> opColor = colorRepository.findByName(product.getColor());
        opColor.ifPresent(c -> {
            prodDB.setColor(c);
        });
        Product productSave =  prodRepository.save(prodDB);
        garbageImages();
        return productSave;
    }

    @Override
    @Transactional
    public Optional<Product> update(ProductRequest product) throws FileReadException, FileWriteException {

        Product productOptional = null;
        List<Images> imgDelete = new ArrayList<>();
        List<Cantidad> cantDelete = new ArrayList<>();
        List<Talla> tallasProduct = new ArrayList<>();
        

        Optional<Product> optionalProd = prodRepository.findById(product.getId());

        if (optionalProd.isPresent()) {

            Product prodDB = optionalProd.orElseThrow();
            prodDB.setName(product.getName());
            prodDB.setPrecio(product.getPrecio());
            
            prodDB.setRebaja(product.isRebaja());
            prodDB.setDisponible(product.isDisponible());

            /////////////////////////////////////
            prodDB.getImages().forEach(i -> {
                imgDelete.add(i);
            });
            saveImg(product).forEach(f -> {
                prodDB.getImages().add(f);
            });
            imgDelete.forEach(i -> {
                prodDB.getImages().remove(i);
            });

            ////////////////////////////////////
           prodDB.getCantidad().forEach(c -> {
            cantDelete.add(c);
           });
            product.getListCantidadDto().forEach(c -> {
                Cantidad cantidad = new Cantidad();
                cantidad.setName(c.getName());
                cantidad.setTotal(c.getTotal());
                prodDB.getCantidad().add(cantidad);
            });
            cantDelete.forEach(c -> {
                prodDB.getCantidad().remove(c);
            });
            //////////////////////////////////////

            product.getTallaList().forEach(p -> {
                Optional<Talla> opTalla = tallaRepository.findByName(p);
                opTalla.ifPresent(t -> {
                    tallasProduct.add(t);
                });
            });
            prodDB.setTallas(tallasProduct);

            Optional<Tipo> opTipo = tipoRepository.findByName(product.getTipo());
            opTipo.ifPresent(t -> {
                prodDB.setTipo(t);
            });

            Optional<Tamanio> opTamanio = tamanioRepository.findByName(product.getTamanio());
            opTamanio.ifPresent(t -> {
                prodDB.setTamanio(t);
            });

            Optional<Color> opColor = colorRepository.findByName(product.getColor());
            opColor.ifPresent(c -> {
                prodDB.setColor(c);
            });

            productOptional = this.onlySave(prodDB);
            garbageImages();
        }
        return Optional.ofNullable(productOptional);
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> op = prodRepository.findById(id);
        op.ifPresent(opBD -> {
            prodRepository.delete(opBD);
            garbageImages();
        });
        return op;
    }

    @Override
    @Transactional(readOnly = true)
    public int garbageImages() {
        int counter = 0;
        File directorio = new File("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\img");
        if (directorio.exists() && directorio.isDirectory()) {
            // obtenemos en un stream los uris del directorio
            Stream<String> urisDirectory = Arrays.asList(directorio.list()).stream();
            // traemos de la bd las uris en una lista string
            List<String> urisBd = imagesService.findAll().stream().map(img -> img.getUri())
                    .collect(Collectors.toList());
            // filtramos en una lista los elementos del directorio que no coincidan con la
            // lista de uris traida de la bd
            List<String> urisDelete = urisDirectory.filter(uris -> !urisBd.contains(uris)).collect(Collectors.toList());
            System.out.println("Elementos encontados= " + urisDelete.size() + " -------->" + urisDelete);
            int urisDeleted = urisDelete.size();
            if (urisDelete.size() != 0) {
                urisDelete.forEach(uri -> {
                    Path pathDelete = Paths
                            .get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\img\\" + uri);
                    try {
                        Files.deleteIfExists(pathDelete);
                    } catch (IOException e) {
                        throw new FileReadException("Error al borrar archivos");
                    }
                });
            }
            System.out.println("Elementos eliminados con exito = " + urisDeleted);
            return urisDeleted;
        } else {
            directorio.mkdirs();
        }
        return counter;
    }

    @SuppressWarnings("null")
    @Transactional(readOnly = true)
    private List<Images> saveImg(ProductRequest product) throws FileReadException, FileWriteException {

        List<Images> listImg = new ArrayList<>();

        if (!product.getId().equals(0L)) {
            Optional<Product> op = prodRepository.findById(product.getId());
            op.ifPresent(prod -> {

                prod.getImages().forEach(li -> {
                    Path pathDelete = Paths
                            .get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\img\\" + li.getUri());
                    if (Files.exists(pathDelete)) {
                        System.out.println("EL ARCHIVO EXISTE*******************xxxxxxxxxxxxxxxx");
                        try {
                            Files.deleteIfExists(pathDelete);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            });
        }

        try {

            for (MultipartFile file : product.getFiles()) {

                String fileName = UUID.randomUUID().toString();
                byte[] bytes = file.getBytes();

                String originalFileName = file.getOriginalFilename();

                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String newFileName = fileName + fileExtension;

                File folder = new File("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\img");
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                Path path = Paths
                        .get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\img\\" + newFileName);
                Files.write(path, bytes);

                Images img = new Images();
                img.setUri(newFileName);
                listImg.add(img);
            }

            return listImg;

        } catch (IOException e) {
            throw new FileReadException("Error al leer el archivo");
        } catch (InvalidPathException e) {
            throw new FileWriteException("Error al cargar imagen");
        } catch (IllegalArgumentException e) {
            throw new FileWriteException("Error al cargar imagen, posibles archivos incompatibles");
        } catch (UnsupportedOperationException e) {
            throw new FileWriteException("Error al cargar imagen, accion no disponible");
        }
    }

}
