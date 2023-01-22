package com.distribuitibackend.controller;


import com.distribuitibackend.Entity.File;
import com.distribuitibackend.Entity.User;
import com.distribuitibackend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController
{
    @Autowired
    private FileService fileService;

    @GetMapping("/tuttiFile")
    public ResponseEntity<List<File>> getAllFile()
    {   try
    {   return new ResponseEntity(fileService.listaFile(), HttpStatus.OK);
    }catch (RuntimeException e)
    {
        return new ResponseEntity("Empty list", HttpStatus.BAD_REQUEST);
    }
    }

    @PostMapping("/filePreferiti")
    public ResponseEntity<List<File>> getAllPreferiti(@RequestBody File file)
    {   try
    {
        return new ResponseEntity(fileService.listaPreferiti(file), HttpStatus.OK);
    }catch (RuntimeException e)
    {   return new ResponseEntity("Empty List", HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/filePerTipo")
    public ResponseEntity<List<File>> trovaFilePerTipo(@PathVariable String string)
    {   try
    {
        return new ResponseEntity(fileService.trovaFilePerTipo(string), HttpStatus.OK);
    }catch (RuntimeException e)
    {
        return new ResponseEntity("FIle non esistente", HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/filePerTitolo")
    public ResponseEntity<List<File>> trovaFilePerTitolo(@PathVariable String string)
    {   try
    {
        return new ResponseEntity(fileService.trovaFilePerTitolo(string), HttpStatus.OK);
    }catch (RuntimeException e)
    {
        return new ResponseEntity("FIle non esistente", HttpStatus.BAD_REQUEST);
    }
    }

    @PostMapping("/aggiungiFile")
    public ResponseEntity<String> aggiungiFile(@RequestBody File file)
    {   try
    {   fileService.aggiungiFile(file);
        return new ResponseEntity("File aggiunto", HttpStatus.OK);
    }catch(RuntimeException e)
    {
        return  new ResponseEntity("Qualcosa è andato storto", HttpStatus.BAD_REQUEST);
    }
    }


    @PostMapping("/aggiungiPreferito")
    public ResponseEntity<String> aggiungiPreferito(@RequestBody File file, @PathVariable String username)
    {   try
    {   fileService.aggiungiPreferito(username, file);
        return new ResponseEntity<String>("File aggiunto con successo ai preferiti", HttpStatus.OK);
    }catch(RuntimeException e)
    {   return new ResponseEntity<String>("File non aggiunto", HttpStatus.BAD_REQUEST);
    }
    }

    @PostMapping("/rimuoviPreferito")
    public ResponseEntity<String> rimuoviPreferito(@RequestBody File file, @PathVariable String user)
    {   try
    {   fileService.rimuoviPreferito(user, file);
        return new ResponseEntity<String>("FIle rimosso con successo", HttpStatus.OK);
    }catch(RuntimeException e)
    {
        return new ResponseEntity<String>("Qualcosa è andato storto", HttpStatus.BAD_REQUEST);
    }
    }
}

