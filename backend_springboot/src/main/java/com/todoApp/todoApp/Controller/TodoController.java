package com.todoApp.todoApp.Controller;


import com.todoApp.todoApp.Model.DTO.RequestObject.TodoRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoResponse;
import com.todoApp.todoApp.Model.Entity.Todo;
import com.todoApp.todoApp.Service.Abstract.TodoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;



    @GetMapping("/todos/{todoId}")
    public TodoResponse getTodo(@PathVariable long todoId){
        return todoService.getTodoById(todoId);
    }
    @GetMapping("/todos")
    public List<TodoResponse> getAllTodos(){
        return todoService.getAll();
    }
    @PostMapping("/todos")
    public void createTodo(@RequestBody TodoRequest todo){
        todoService.addTodo(todo);
    }
    @PutMapping("/todos/{todoId}")
    public TodoResponse updateTodo(@PathVariable long todoId, @RequestBody TodoRequest todo){
        return todoService.updateTodo(todoId,todo);
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable long todoId){
         todoService.deleteTodoById(todoId);
    }



}
