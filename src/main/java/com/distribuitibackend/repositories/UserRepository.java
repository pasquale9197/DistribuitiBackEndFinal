package com.distribuitibackend.repositories;

import com.distribuitibackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository<T extends User> extends JpaRepository<T,Long>
{
    boolean existsByEmail(String email);
    boolean existsByNomeutente(String nomeutente);
    User findByEmail(String email);
    User findByNomeutente(String nomeutente);
    boolean existsByPassword(String password);
}
