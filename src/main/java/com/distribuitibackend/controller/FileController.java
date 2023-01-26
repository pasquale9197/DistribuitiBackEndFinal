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


}

