package com.igtassignment.todo.todoassignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection="todos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Todo {

    @Id
    private String id;

    @Indexed(unique=true)
    private String title;

    private Boolean completed = false;

    private Date createdAt = new Date();
}
