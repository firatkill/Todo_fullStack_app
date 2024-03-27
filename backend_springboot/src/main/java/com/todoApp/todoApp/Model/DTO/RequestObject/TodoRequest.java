package com.todoApp.todoApp.Model.DTO.RequestObject;

import com.todoApp.todoApp.Model.Entity.Todo;

public record TodoRequest(String todoName, String todoDescription, String todoColor, String date, boolean completed) {

    public Todo convertToTodo(){
        return Todo.builder().todoName(this.todoName).todoColor(this.todoColor).todoDescription(this.todoDescription).date(this.date).completed(this.completed).build();
    }
}
