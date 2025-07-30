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
import org.springframework.web.multipart.MultipartFile;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.FileReadException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.FileWriteException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.CollectionTwo;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.Pages;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.PagesRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.CollectionTwoRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.PagesRepository;

@Service
public class CollectionTwoServiceImpl implements CollectionTwoService{

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private CollectionTwoRepository collectionTwoRepository;

    @Override
    public List<CollectionTwo> findAll() {
        return (List<CollectionTwo>)collectionTwoRepository.findAll();
    }

    
    @SuppressWarnings("null")
    @Override
    public List<CollectionTwo> saveCollectionTwo(PagesRequest pages) {
        List<CollectionTwo> listColTwo = new ArrayList<>();
        
        if (!pages.getId().equals(0L)) {
            Optional<Pages> opPages = pagesRepository.findById(pages.getId());
            opPages.ifPresent(op -> {
                op.getCollectionTwo().forEach(coll -> {
                    Path pathDelete = Paths
                            .get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\collectionTwo\\"
                            + coll.getUri());
                    if (Files.exists(pathDelete)) {
                        System.out.println("El archivo existe y sera eliminado");
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
            
            for (MultipartFile file : pages.getFilesTwo()) {

                String fileName = UUID.randomUUID().toString();
                byte[] bytes = file.getBytes();
                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String newFileName = fileName + fileExtension;
                
                File folder = new File("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\collectionTwo");
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                Path path = Paths.get(
                    "C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\collectionTwo\\" + newFileName);
                Files.write(path, bytes);
                
                CollectionTwo colTwo = new CollectionTwo();
                colTwo.setUri(newFileName);
                listColTwo.add(colTwo);
            }
            return listColTwo;
            
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
    
    @Override
    public int garbageFileCollectionTwo() {
        int counter = 0;
        File directorio = new File("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\collection");
        if (directorio.exists() && directorio.isDirectory()) {
            // obtenemos en un stream los uris del directorio
            Stream<String> urisDirectory = Arrays.asList(directorio.list()).stream();
            // traemos de la bd las uris en una lista string
            List<String> urisBd = this.findAll().stream().map(img -> img.getUri())
                    .collect(Collectors.toList());
            // filtramos en una lista los elementos del directorio que no coincidan con la
            // lista de uris traida de la bd
            List<String> urisDelete = urisDirectory.filter(uris -> !urisBd.contains(uris)).collect(Collectors.toList());
            System.out.println("Elementos encontados= " + urisDelete.size() + " ------->" + urisDelete);
            int urisDeleted = urisDelete.size();
            if (urisDelete.size() != 0) {
                urisDelete.forEach(uri -> {
                    Path pathDelete = Paths
                            .get("C:\\Users\\Cesar\\Desktop\\React\\suelasApp4.1client\\public\\collection" + uri);
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
}
