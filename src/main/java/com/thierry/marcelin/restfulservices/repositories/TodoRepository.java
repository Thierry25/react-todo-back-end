package com.thierry.marcelin.restfulservices.repositories;

import com.thierry.marcelin.restfulservices.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUsername(String username);
}