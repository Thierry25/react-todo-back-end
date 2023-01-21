package com.thierry.marcelin.restfulservices.services;

import com.thierry.marcelin.restfulservices.exceptions.TodoNotFound;
import com.thierry.marcelin.restfulservices.models.Todo;
import com.thierry.marcelin.restfulservices.repositories.TodoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoDaoImpl implements TodoDao{
    private final TodoRepository todoRepository;

    public TodoDaoImpl(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    @Override
    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    @Override
    public Todo addTodo(Todo todo) {
       return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Todo todo) {
       todoRepository.delete(todo);
    }

    @Override
    public Todo findById(int id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFound("Not Found"));
    }

    @Override
    public Todo updateTodo(Todo todo) {
       return todoRepository.save(todo);
    }
}
