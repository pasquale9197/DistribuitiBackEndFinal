package com.distribuitibackend.controller;

import com.distribuitibackend.Entity.User;
import com.distribuitibackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")

public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> creaUtente(@RequestBody User u)
    {   try
    {   userService.registrazione(u);
        return new ResponseEntity("200", HttpStatus.OK);
    }catch(RuntimeException e)
    {   return new ResponseEntity("User already exists!", HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/getEmail")
    public ResponseEntity<User> getEmailUtente(@RequestParam String email)
    {   try
    {   if(!userService.esisteUtente(email))
        return new ResponseEntity("Email non esistente!", HttpStatus.BAD_REQUEST);
    }catch (RuntimeException e)
    {   User u = userService.getUtente(email);
        return new ResponseEntity(u, HttpStatus.OK);
    }
        return null;
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User u)
    {   try
    {   if(userService.login(u));
        return new ResponseEntity(true, HttpStatus.BAD_REQUEST);
    }catch (RuntimeException e)
    {   return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }
    }
}

