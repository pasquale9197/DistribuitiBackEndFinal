package com.distribuitibackend.services;

import com.distribuitibackend.Entity.File;
import com.distribuitibackend.Entity.User;
import com.distribuitibackend.repositories.FileIndicizzatiRepository;
import com.distribuitibackend.repositories.FileRepository;
import com.distribuitibackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileIndicizzatiRepository fileIndicizzatiRepository;

    @Transactional(readOnly = true)
    public List<File> listaFile()
    {   if(userRepository.findAll().size() == 0)
        throw new RuntimeException("Empty list");
        return fileRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void aggiungiFile(File file)
    {   fileRepository.saveAndFlush(file);

    }

    @Transactional(readOnly = false)
    public void aggiungiFile(User user, String typefile, String file, String titolo, String descrizione)
    {   File nuovofile = new File(user, typefile, file, titolo, descrizione);
        fileRepository.saveAndFlush(nuovofile);
    }

    @Transactional(readOnly = false)
    public void aggiungiFile(User user, String typefile, String file, String titolo)
    {   File nuovofile = new File(user, typefile, file, titolo);
        fileRepository.saveAndFlush(nuovofile);
    }

    @Transactional(readOnly = true)
    public List<File> trovaFilePerTipo(String tipo)
    {   List listaRet = new ArrayList();
        try
        {   if(!fileRepository.existsByTypefile(tipo))
            throw new RuntimeException("File doesn't exists!");
        else
        {   listaRet.add(fileRepository.findByTypefile(tipo));
            if(!fileIndicizzatiRepository.existsByTypefile(tipo))
                fileIndicizzatiRepository.saveAndFlush(fileRepository.findByTypefile(tipo));
        }
        }catch(RuntimeException e)
        {   List<File>listaFile = fileRepository.findAll();
            for(File f : listaFile)
            {   if(f.getTypefile().contains(tipo))
            {   listaFile.add(f);
                if(!fileIndicizzatiRepository.existsByTypefile(tipo))
                    fileIndicizzatiRepository.saveAndFlush(f);
            }
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
        {   listaRet.add(fileRepository.findByTitolo(titolo));
            if(!fileIndicizzatiRepository.existsByTitolo(titolo))
                fileIndicizzatiRepository.saveAndFlush(fileRepository.findByTitolo(titolo));
        }
        }catch (RuntimeException e)
        {   List<File>listaFile = fileRepository.findAll();
            for(File f : listaFile)
            {   if(f.getTitolo().contains(titolo))
            {   listaFile.add(f);
                if(!fileIndicizzatiRepository.existsByTitolo(titolo))
                    fileIndicizzatiRepository.saveAndFlush(f);
            }
            }
        }
        return listaRet;
    }


    @Transactional(readOnly = true)
    public List<User> listaPreferiti(File file)
    {   try
    {   if(fileRepository.findById(file.getId()).getListaUser().isEmpty())
        throw new RuntimeException("Empty List");
    }catch (RuntimeException e)
    {
        return fileRepository.findById(file.getId()).getListaUser();
    }
        return null;
    }

    @Transactional(readOnly = false)
    public void aggiungiPreferito(User u, File f)
    {   List<User> list = fileRepository.findById(f.getId()).getListaUser();
        list.add(u);
        fileRepository.findById(f.getId()).setListaUser(list);
        fileRepository.saveAndFlush(f);
    }

    @Transactional(readOnly = false)
    public void rimuoviPreferito(User u, File f)
    {   List<User> list = fileRepository.findById(f.getId()).getListaUser();
        list.remove(u);
        fileRepository.findById(f.getId()).setListaUser(list);
        fileRepository.saveAndFlush(f);
    }
}