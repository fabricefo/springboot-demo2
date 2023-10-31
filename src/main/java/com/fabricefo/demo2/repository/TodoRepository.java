package com.fabricefo.demo2.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.fabricefo.demo2.model.Todo;

import reactor.core.publisher.Flux;

@Repository
public interface TodoRepository extends R2dbcRepository<Todo, Integer>{
    Flux<Todo> findByTitleContaining(String title);
}
