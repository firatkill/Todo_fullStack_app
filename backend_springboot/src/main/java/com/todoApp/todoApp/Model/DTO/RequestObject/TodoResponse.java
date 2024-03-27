package com.todoApp.todoApp.Model.DTO.RequestObject;

import com.todoApp.todoApp.Model.Entity.Todo;
import com.todoApp.todoApp.Model.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {

    private Long id;
    private String todoName;
    private String todoDescription;
    private String todoColor;
    private String date;
    private boolean completed;
    private Instant createdAt ;
    private Long userId;


   public void convertFromTodo(Todo todo){
        this.id=todo.getId();
        this.todoName=todo.getTodoName();
        this.todoDescription=todo.getTodoDescription();
        this.todoColor=todo.getTodoColor();
        this.date=todo.getDate();
        this.completed=todo.getCompleted();
        this.createdAt=todo.getCreatedAt();
        this.userId=todo.getUser().getId();

    }


}
