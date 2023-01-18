package com.thierry.marcelin.restfulservices.services;

import com.thierry.marcelin.restfulservices.exceptions.TodoNotFound;
import com.thierry.marcelin.restfulservices.models.Todo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoDaoImpl implements TodoDao{

    private static final List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static{
        todos.add(new Todo(++todosCount, "thierrymarcelin", "Learn Spring Boot", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(++todosCount, "thierrymarcelin", "Learn React", LocalDate.now().plusMonths(4), false));
        todos.add(new Todo(++todosCount, "thierrymarcelin", "Learn Javascript", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(++todosCount, "fmarcelin", "Learn DevOps", LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++todosCount, "fmarcelin", "Learn CI/CD", LocalDate.now().plusMonths(4), false));
    }
    @Override
    public List<Todo> findByUsername(String username) {
        return todos.stream().filter((todo) -> todo.getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public Todo addTodo(Todo todo) {
        todos.add(todo);
        return todos.get(todos.size() -1);
    }

    @Override
    public void deleteTodo(Todo todo) {
        todos.remove(todo);
    }

    @Override
    public Todo findById(int id) {
        return todos.stream()
                .filter((todo) -> todo.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TodoNotFound("Not Found"));
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        var updatedTodo = findById(id);
        todo.setId(id);
        return todos.set(updatedTodo.getId() - 1, todo);
    }
}
