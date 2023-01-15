package com.distribuitibackend.services;

import com.distribuitibackend.Entity.User;
import com.distribuitibackend.repositories.FileRepository;
import com.distribuitibackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;


    @Transactional(readOnly = true)
    public List<User> getAllUsers()
    {   return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUtente(String email)
    {   if(!userRepository.existsByEmail(email))
        throw new RuntimeException("User doesn't exists");
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public User getNomeUtente(String nomeutente)
    {   if(!userRepository.existsByNomeutente(nomeutente))
        throw new RuntimeException("User doesn't exists");
        return userRepository.findByNomeutente(nomeutente);
    }
    @Transactional(readOnly = true)
    public boolean esisteUtente(String email)
    {   if(!userRepository.existsByEmail(email))
        return false;
        return true;
    }

    @Transactional(readOnly = true)
    public boolean login(User u)
    {   if(userRepository.existsByEmail(u.getEmail()) || userRepository.existsByNomeutente(u.getNomeutente()))
    {   if(userRepository.existsByPassword(u.getPassword()))
        return true;
    }
        throw new RuntimeException("User doesn't exists");
    }


    @Transactional(readOnly = false)
    public void registrazione(String nome, String cognome, String email, String password, String nomeutente)
    {   if(userRepository.existsByEmail(email))
        throw new RuntimeException("Email non disponibile");
        if(userRepository.existsByNomeutente(nomeutente))
            throw new RuntimeException("Nome utente non disponibile");
        User nuovoUtente = new User(nome, cognome, email, password, nomeutente,false);
        userRepository.saveAndFlush(nuovoUtente);
    }

    @Transactional(readOnly = false)
    public void registrazione(User utente)
    {   if(userRepository.existsByEmail(utente.getEmail()))
        throw new RuntimeException("Email non disponibile");
        if(userRepository.existsByNomeutente(utente.getNomeutente()))
        {    throw new RuntimeException("Nome utente non disponibile");
        }
        userRepository.saveAndFlush(utente);
    }


}
