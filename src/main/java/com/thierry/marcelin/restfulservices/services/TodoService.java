package com.thierry.marcelin.restfulservices.services;

import com.thierry.marcelin.restfulservices.models.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao){
        this.todoDao = todoDao;
    }

    public List<Todo> getMyTodos(String username){
        return todoDao.findByUsername(username);
    }

    public Todo getTodo(Integer id){
        return todoDao.findById(id);
    }

    public void removeTodo(Todo todo){
         todoDao.deleteTodo(todo);
    }

    public Todo updateTodo(Integer id, Todo todo){
        return todoDao.updateTodo(id, todo);
    }

}
