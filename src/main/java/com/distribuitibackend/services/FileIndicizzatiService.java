package com.distribuitibackend.services;

import com.distribuitibackend.Entity.File;
import com.distribuitibackend.repositories.FileRepository;
import com.distribuitibackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileIndicizzatiService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;

    @Transactional(readOnly = true)
    public List<File> listaFile()
    {   if(userRepository.findAll().size() == 0)
            throw new RuntimeException("Empty list");
        return fileRepository.findAll();
    }


    @Transactional(readOnly = true)
    public List<File> trovaFilePerTipo(String tipo)
    {   List listaRet = new ArrayList();
        try
        {   if(!fileRepository.existsByTypefile(tipo))
            throw new RuntimeException("File doesn't exists!");
        else
            fileRepository.findByTypefile(tipo);
        }catch(RuntimeException e)
        {   List<File>listaFile = fileRepository.findAll();
            for(File f : listaFile)
            {   if(f.getTypefile().contains(tipo))
                listaFile.add(f);
            }
        }
        return listaRet;
    }

    @Transactional(readOnly = true)
    public List<File> trovaFilePerTitolo(String titolo)
    {   List listaRet = new ArrayList();
        try
        {   if(!fileRepository.existsByTitolo(titolo))
            throw new RuntimeException("File doesn't exists!");
        else
            fileRepository.findByTitolo(titolo);
        }catch (RuntimeException e)
        {   List<File>listaFile = fileRepository.findAll();
            for(File f : listaFile)
            {   if(f.getTitolo().contains(titolo))
                listaFile.add(f);
            }
        }
        return listaRet;
    }


}