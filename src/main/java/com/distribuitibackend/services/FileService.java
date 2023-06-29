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
public class FileService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileIndicizzatiRepository fileIndicizzatiRepository;

    @Transactional(readOnly = true)
    public List<File> listaFile() {
        if (userRepository.findAll().size() == 0)
            throw new RuntimeException("Empty list");
        return fileRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void aggiungiFile(byte[] filePassato) {
        File f = new File();
        f.setId_user(new User());
        f.setTypefile("type");
        f.setTitolo("titolo");
        f.setFile(filePassato);
        f.setDescrizione("descr");
        fileRepository.save(f);
    }

    @Transactional(readOnly = false)
    public void aggiungiFile(User user, String typefile, byte[] file, String titolo, String descrizione) {
        File nuovofile = new File(user, typefile, file, titolo, descrizione);
        fileRepository.saveAndFlush(nuovofile);
    }

    @Transactional(readOnly = false)
    public void aggiungiFile(User user, String typefile, byte[] file, String titolo) {
        File nuovofile = new File(user, typefile, file, titolo);
        fileRepository.saveAndFlush(nuovofile);
    }

    @Transactional(readOnly = true)
    public List<File> trovaFilePerTipo(String tipo) {
        System.out.println(tipo);
        List listaRet = new ArrayList();
        try {
            if (!fileRepository.existsByTypefile(tipo))
                throw new RuntimeException("Files don't exists!");
            else {
                    listaRet = fileRepository.findByTypefile(tipo);
                    fileIndicizzatiRepository.deleteAll();
            }
        } catch (RuntimeException e) {
            List<File> listaFile = fileRepository.findAll();
            for (File f : listaFile) {
                if (f.getTypefile().contains(tipo)) {
                    listaFile.add(f);
                    if (!fileIndicizzatiRepository.existsById(f.getId()))
                        fileIndicizzatiRepository.saveAndFlush(f);
                }
            }
        }
        System.out.println(listaRet);
        return listaRet;
    }

    @Transactional(readOnly = true)
    public File trovaFilePerId(int id)
    {   return fileRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<File> trovaFilePerTitolo(String titolo) {
        List listaRet = new ArrayList();
        try
        {   if (!fileRepository.existsByTitolo(titolo))
                throw new RuntimeException("File doesn't exists!");
            else
            {   listaRet = fileRepository.findByTitolo(titolo);
                fileIndicizzatiRepository.deleteAll();
            }
        } catch (RuntimeException e) {
            List<File> listaFile = fileRepository.findAll();
            for (File f : listaFile) {
                if (f.getTitolo().contains(titolo)) {
                    listaFile.add(f);
                    if (!fileIndicizzatiRepository.existsById(f.getId()))
                        fileIndicizzatiRepository.saveAndFlush(f);
                }
            }
        }
        System.out.println(listaRet);
        return listaRet;
    }


    @Transactional(readOnly = true)
    public List<File> listaPreferiti(String username) {
        List<File> filePref = new ArrayList<>();
        ArrayList<File> file = (ArrayList<File>) fileRepository.findAll();
        System.out.println(file);
        User user = userRepository.findByNomeutente(username);
        for (File f : file) {
            for (User u : f.getListaUser()) {
                System.out.println("ciao");
                if (u.getId() == user.getId()) {
                    filePref.add(f);
                    System.out.println("ciaoooo");
                    break;
                }
            }
        }
        return filePref;
    }
}