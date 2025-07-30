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
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.CollectionOne;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.CollectionTwo;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.Pages;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.PagesRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.PagesRepository;

@Service
public class PagesServiceImpl implements PagesService {

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private CollectionOneService collectionOneService;

    @Autowired
    private CollectionTwoService collectionTwoService;

    @Override
    @Transactional(readOnly = true)
    public List<Pages> findAll() {
        return (List<Pages>) pagesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pages> findById(Long id) {
        return pagesRepository.findById(id);
    }

    @Override
    @Transactional
    public Pages onlySave(Pages pages) {
        return pagesRepository.save(pages);
    }

    @Override
    @Transactional
    public Pages save(PagesRequest pages) {

        Pages pagesDB = new Pages();
        pagesDB.setName(pages.getName());

        pagesDB.setImageOne(getImgUri(pages.getImageOne(), pages));
        pagesDB.setImageTwo(getImgUri(pages.getImageTwo(), pages));
        pagesDB.setImageThree(getImgUri(pages.getImageThree(), pages));

        pagesDB.setTextOne(pages.getTextOne());
        pagesDB.setTextTwo(pages.getTextTwo());

        collectionOneService.saveCollectionOne(pages).forEach(p -> {
            pagesDB.getCollectionOne().add(p);
        });

        collectionTwoService.saveCollectionTwo(pages).forEach(p -> {
            pagesDB.getCollectionTwo().add(p);
        });
        Pages pagesSave = pagesRepository.save(pagesDB);
        garbageCollectOne();
        garbageCollectTwo();
        garbageImgPublic();
        return pagesSave;
    }

    @Override
    @Transactional
    public Optional<Pages> update(PagesRequest pages) {
        Pages pagesDB = null;
        List<CollectionOne> deleteOneList = new ArrayList<>();
        List<CollectionTwo> deleteTwoList = new ArrayList<>();

        Optional<Pages> opPages = pagesRepository.findById(pages.getId());
        if (opPages.isPresent()) {
            Pages op = opPages.orElseThrow();
            op.setName(pages.getName());

            op.setImageOne(getImgUri(pages.getImageOne(), pages));
            op.setImageTwo(getImgUri(pages.getImageTwo(), pages));
            op.setImageThree(getImgUri(pages.getImageThree(), pages));

            op.setTextOne(pages.getTextOne());
            op.setTextTwo(pages.getTextTwo());

            op.getCollectionOne().forEach(co -> {
                deleteOneList.add(co);
            });
            op.getCollectionTwo().forEach(ct -> {
                deleteTwoList.add(ct);
            });

            collectionOneService.saveCollectionOne(pages).forEach(co -> {
                op.getCollectionOne().add(co);
            });
            collectionTwoService.saveCollectionTwo(pages).forEach(ct -> {
                op.getCollectionTwo().add(ct);
            });

            deleteOneList.forEach(d -> {
                op.getCollectionOne().remove(d);
            });
            deleteTwoList.forEach(d -> {
                op.getCollectionTwo().remove(d);
            });

            pagesDB = this.onlySave(op);
            garbageCollectOne();
            garbageCollectTwo();
            garbageImgPublic();
        }
        return Optional.ofNullable(pagesDB);
    }

    @Override
    @Transactional
    public Optional<Pages> remove(Long id) {
        Optional<Pages> op = pagesRepository.findById(id);
        op.ifPresent(opBD -> {
            pagesRepository.delete(opBD);
            garbageCollectOne();
            garbageCollectTwo();
            garbageImgPublic();
        });
        return op;
    }

    @Override
    public int garbageCollectOne() {
        return collectionOneService.garbageFileCollectionOne();
    }

    @Override
    public int garbageCollectTwo() {
        return collectionTwoService.garbageFileCollectionTwo();
    }

    @SuppressWarnings("null")
    @Transactional(readOnly = true)
    private String getImgUri(MultipartFile file, PagesRequest pages) {

        /*if (!pages.getId().equals(0L)) {
            Optional<Pages> op = pagesRepository.findById(pages.getId());
            op.ifPresent(oimg -> {
                Path pathDelete = Paths.get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\imgpublicy\\"
                        + oimg.getImageOne());
                if (Files.exists(pathDelete)) {
                    System.out.println("El archivo existe y sera eliminado");
                    try {
                        Files.deleteIfExists(pathDelete);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }*/

        try {
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;

            File folder = new File("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\imgpublicy");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            Path path = Paths
                    .get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\imgpublicy\\" + newFileName);
            Files.write(path, bytes);
            return newFileName;

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

    @Transactional(readOnly = true)
    private void garbageImgPublic() {

        File directorio = new File("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\imgpublicy");
        if (directorio.exists() && directorio.isDirectory()) {
            // obtenemos en un stream los uris del directorio
            Stream<String> urisDirectory = Arrays.asList(directorio.list()).stream();
            List<String> urisListOne = this.findAll().stream().map(p -> p.getImageOne()).collect(Collectors.toList());
            List<String> urisListTwo = this.findAll().stream().map(p -> p.getImageTwo()).collect(Collectors.toList());
            List<String> urisListThree = this.findAll().stream().map(p -> p.getImageThree())
                    .collect(Collectors.toList());
            List<String> urisBd= new ArrayList<>();
            urisBd.addAll(urisListOne);
            urisBd.addAll(urisListTwo);
            urisBd.addAll(urisListThree);
                    // filtramos en una lista los elementos del directorio que no coincidan con la
            // lista de uris traida de la bd
            List<String> urisDelete = urisDirectory.filter(uris -> !urisBd.contains(uris)).collect(Collectors.toList());
            System.out.println("Elementos encontados = " + urisDelete.size() + " ------->" + urisDelete);
            if (urisDelete.size() != 0) {
                urisDelete.forEach(uri -> {
                    Path pathDelete = Paths
                            .get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\imgpublicy\\" + uri);
                    try {
                        Files.deleteIfExists(pathDelete);
                    } catch (IOException e) {
                        throw new FileReadException("Error al borrar archivos");
                    }
                });
            }
        }else {
            directorio.mkdirs();
        }
    }

}
