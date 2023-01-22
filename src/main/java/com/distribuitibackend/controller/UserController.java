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
    public ResponseEntity<User> creaUtente(@RequestBody User u)
    {   try
        {   userService.registrazione(u);
            return new ResponseEntity("200", HttpStatus.OK);
        }catch(RuntimeException e)
        {   return new ResponseEntity("User already exists!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmail/{email}")
    public ResponseEntity<User> getEmailUtente(@PathVariable String email)
    {   try
        {   if(!userService.esisteUtente(email))
                return new ResponseEntity("Email non esistente!", HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e)
        {   User u = userService.getUtente(email);
            return new ResponseEntity(u, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/login/{u}&{pass}")
    public ResponseEntity<Boolean> login(@PathVariable String u, @PathVariable double pass)
    {   try
        {   if(userService.login(u,pass));
            {
                return new ResponseEntity(true, HttpStatus.OK);
            }
        }catch (RuntimeException e)
        {   return new ResponseEntity("false porco dio", HttpStatus.BAD_REQUEST);
        }
    }
}

