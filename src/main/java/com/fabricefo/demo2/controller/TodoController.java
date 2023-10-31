package com.fabricefo.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fabricefo.demo2.model.Todo;
import com.fabricefo.demo2.service.TodoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TodoController {
    
    @Autowired
    TodoService todoService;

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Todo> getAllTodos(@RequestParam(required = false) String title) {
        if (title == null)
        return todoService.findAll();
        else
        return todoService.findByTitleContaining(title);
    }

    @GetMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Todo> getTodoById(@PathVariable("id") int id) {
        return todoService.findById(id);
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Todo> createTodo(@RequestBody Todo todo) {
        return todoService.save(new Todo(todo.getTitle(), todo.getDescription()));
    }

    @PutMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Todo> updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTodo(@PathVariable("id") int id) {
        return todoService.deleteById(id);
    }

    @DeleteMapping("/todos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllTodos() {
        return todoService.deleteAll();
    }    
}
