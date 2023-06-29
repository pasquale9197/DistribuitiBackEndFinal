package com.distribuitibackend.controller;


import com.distribuitibackend.Entity.File;
import com.distribuitibackend.repositories.FileRepository;
import com.distribuitibackend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController
{
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fr;

    @GetMapping("/tuttiFile")
    public ResponseEntity<List<File>> getAllFile()
    {   try
        {   return new ResponseEntity(fileService.listaFile(), HttpStatus.OK);
        }catch (RuntimeException e)
        {
            return new ResponseEntity("Empty list", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filePreferiti/{username}")
    public ResponseEntity<List<File>> getAllPreferiti(@PathVariable String username)
    {   try
        {   return new ResponseEntity(fileService.listaPreferiti(username), HttpStatus.OK);
        }catch (RuntimeException e)
        {   return new ResponseEntity("Empty List", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filePerTipo/{string}")
    public ResponseEntity<List<File>> trovaFilePerTipo(@PathVariable String string)
    {   try
        {
            return new ResponseEntity(fileService.trovaFilePerTipo(string), HttpStatus.OK);
        }catch (RuntimeException e)
        {
            return new ResponseEntity("FIle non esistente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getFileId/{id}")
    public ResponseEntity<File> trovaFilePerId(@PathVariable int id)
    {   try
    {
        return new ResponseEntity(fileService.trovaFilePerId(id), HttpStatus.OK);
    }catch (RuntimeException e)
    {
        return new ResponseEntity("FIle non esistente", HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/filePerTitolo/{string}")
    public ResponseEntity<List<File>> trovaFilePerTitolo(@PathVariable String string)
    {   try
        {
            return new ResponseEntity(fileService.trovaFilePerTitolo(string), HttpStatus.OK);
        }catch (RuntimeException e)
        {
            return new ResponseEntity("FIle non esistente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/prova")
    private void prova(){
        byte[] file = fr.findById(152).getFile();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("C:\\Users\\pasqu\\Desktop\\prova.pdf");
            fos.write(file);
            fos.close();
        } catch(IOException e){
            System.out.println("errore");
        }
    }

    @PostMapping("/create")
    public ResponseEntity aggiungiFile(@RequestBody byte[] file)
    {
        try
        {   fileService.aggiungiFile(file);
            return new ResponseEntity("File aggiunto", HttpStatus.OK);
        }catch(RuntimeException e)
        {
            return  new ResponseEntity("Qualcosa è andato storto", HttpStatus.BAD_REQUEST);
        }
    }


}

