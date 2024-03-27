package com.todoApp.todoApp.Service.Abstract;

import com.todoApp.todoApp.Model.DTO.RequestObject.TodoRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoResponse;
import com.todoApp.todoApp.Model.Entity.Todo;
import com.todoApp.todoApp.Model.Entity.User;

import java.util.List;

public interface TodoService {
    void addTodo(TodoRequest todo);
    void deleteTodoById(long id);
    TodoResponse updateTodo(long todoId,TodoRequest todo);
    TodoResponse getTodoById(long id);
    TodoResponse getTodoByName(String name);
    List<TodoResponse> getAll();

}
