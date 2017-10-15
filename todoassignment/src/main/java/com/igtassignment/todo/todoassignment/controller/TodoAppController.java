package com.igtassignment.todo.todoassignment.controller;


import com.igtassignment.todo.todoassignment.entity.Todo;
import com.igtassignment.todo.todoassignment.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoAppController {


    TodoRepository todoRepository;

    @Autowired
    public TodoAppController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdOn");
        return todoRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        todo.setCompleted(false);
        todo.setCreatedOn(new Date());
        return todoRepository.save(todo);
    }

    @GetMapping(value="/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") String id) {

        Optional<Todo> todo = Optional.ofNullable(todoRepository.findOne(id));

        return todo.isPresent() ? new ResponseEntity<>(todo.get(), HttpStatus.OK) :
                                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody Todo todo) {

        Todo todoData = todoRepository.findOne(id);

        if(todoData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        todoData.setTitle(todo.getTitle());
        todoData.setCompleted(todo.getCompleted());
        todoData.setModifiedOn(new Date());
        Todo updatedTodo = todoRepository.save(todoData);

        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping(value="/todos/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
        todoRepository.delete(id);
    }
}

