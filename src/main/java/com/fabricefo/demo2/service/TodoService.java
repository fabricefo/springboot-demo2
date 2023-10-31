package com.fabricefo.demo2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabricefo.demo2.model.Todo;
import com.fabricefo.demo2.repository.TodoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoService {
    
    @Autowired
    TodoRepository todoRepository;

    public Flux<Todo> findAll() {
        return todoRepository.findAll();
    }
    public Flux<Todo> findByTitleContaining(String title) {
        return todoRepository.findByTitleContaining(title);
    }
    public Mono<Todo> findById(int id) {
        return todoRepository.findById(id);
    }
    public Mono<Todo> save(Todo todo) {
        return todoRepository.save(todo);
    }
    public Mono<Todo> update(int id, Todo todo) {
        return todoRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
            .flatMap(optionalTodo -> {
              if (optionalTodo.isPresent()) {
                todo.setId(id);
                return todoRepository.save(todo);
              }
    
              return Mono.empty();
            });
      }
    
      public Mono<Void> deleteById(int id) {
        return todoRepository.deleteById(id);
      }
    
      public Mono<Void> deleteAll() {
        return todoRepository.deleteAll();
      }
}
