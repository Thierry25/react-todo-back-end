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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;


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
                TODO_FILTER, "id","description", "targetDate",  "done");
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

    @PatchMapping("/users/{username}/todos/{id}")
    public MappingJacksonValue updateTodo(@PathVariable String username, @PathVariable Integer id, @Valid @RequestBody Todo todo){
        todo.setUsername(username);
        var updatedTodo = todoService.updateTodo(id, todo);
        var entityModel = EntityModel.of(updatedTodo);
        var link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllTodos(username));
        entityModel.add(link.withRel("all-my-links"));
        return BeanFiltering.filter(entityModel, TODO_FILTER, "id", "description", "targetDate", "done");
    }

    @PostMapping("/users/{username}/todos")
    public MappingJacksonValue createNewTodo(@PathVariable String username, @Valid @RequestBody Todo todo){
        todo.setUsername(username);
        var newTodo = todoService.addTodo(todo);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newTodo.getId())
                        .toUri();
        var response = ResponseEntity.created(location).body(newTodo);
        return BeanFiltering.filter(response, TODO_FILTER, "id", "description", "targetDate");
    }


}
