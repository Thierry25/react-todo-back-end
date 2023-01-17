package com.thierry.marcelin.restfulservices.controllers;

import com.thierry.marcelin.restfulservices.models.Todo;
import com.thierry.marcelin.restfulservices.services.TodoService;
import com.thierry.marcelin.restfulservices.utils.BeanFiltering;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;
    private static final String TODO_FILTER = "TodoFilter";
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public MappingJacksonValue retrieveAllTodos(@PathVariable String username){
        var todos = todoService.getMyTodos(username);
        return BeanFiltering.filter(todos,
                TODO_FILTER, "id","description", "targetDate", "done");
    }

    @GetMapping("/users/{username}/todos/{id}")
    public MappingJacksonValue retrieveTodo(@PathVariable String username, @PathVariable Integer id){
        var todo = todoService.getTodo(id);
        var entity = EntityModel.of(todo);
        var link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllTodos(username));
        entity.add(link.withRel("all-my-links"));
        return BeanFiltering.filter(entity,
                TODO_FILTER,"description", "targetDate", "done" );
    }
    @DeleteMapping("/users/{username}/todos/{id}")
    public void deleteTodo(@PathVariable String username, @PathVariable Integer id){
       todoService.removeTodo(todoService.getTodo(id));
    }
}
