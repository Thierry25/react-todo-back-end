package com.thierry.marcelin.restfulservices.services;

import com.thierry.marcelin.restfulservices.models.Todo;

import java.util.List;

public interface TodoDao {
    List<Todo> findByUsername(String username);
    Todo addTodo(Todo todo);
    void deleteTodo(Todo todo);
    Todo findById(int id);
    void updateTodo(int id, Todo todo);
}
