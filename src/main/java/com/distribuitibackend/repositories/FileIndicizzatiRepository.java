package com.distribuitibackend.repositories;

import com.distribuitibackend.Entity.File;
import com.distribuitibackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileIndicizzatiRepository<T extends File> extends JpaRepository<T, Long>
{
    boolean existsById(long id);
    boolean existsByTypefile(String type);
    boolean existsByTitolo(String titolo);
    List<File> findByTitolo(String titolo);
    File findByTypefile(String type);
    File findById(long id);

}

