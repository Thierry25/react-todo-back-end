package com.thierry.marcelin.restfulservices.models;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@JsonFilter("TodoFilter")
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    @Size(min = 5, message="Please enter at least 5 characters to your description")
    private String description;
    @FutureOrPresent(message = "Please enter a future or present date")
    private LocalDate targetDate;
    private boolean done;

    public Todo(Integer id, String username, String description, LocalDate targetDate, boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    public Todo(){}

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }
}
