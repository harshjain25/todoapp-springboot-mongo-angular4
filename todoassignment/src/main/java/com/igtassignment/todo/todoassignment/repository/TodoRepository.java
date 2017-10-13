package com.igtassignment.todo.todoassignment.repository;

import com.igtassignment.todo.todoassignment.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{
}
