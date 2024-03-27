package com.todoApp.todoApp.Service.Concrete;

import com.todoApp.todoApp.Model.DTO.RequestObject.TodoRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoResponse;
import com.todoApp.todoApp.Model.Entity.Todo;
import com.todoApp.todoApp.Model.Entity.User;
import com.todoApp.todoApp.Repository.TodoRepository;
import com.todoApp.todoApp.Repository.UserRepository;
import com.todoApp.todoApp.Service.Abstract.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;



    @Override
    public void addTodo(TodoRequest todo) {
      Todo todoToSave= todo.convertToTodo();
      todoToSave.setUser(getUser());
      todoRepository.save(todoToSave);

    }
    @Override
    public void deleteTodoById(long todoId) {
        todoRepository.deleteById(todoId);
    }

    @Override
    public TodoResponse updateTodo(long todoId, TodoRequest todo) {
     Optional<Todo> optTodo=todoRepository.findById(todoId);

     if(optTodo.isPresent()){
         Todo  todoToUpdate=optTodo.get();
         todoToUpdate.setTodoName(todo.todoName());
         todoToUpdate.setTodoColor(todo.todoColor());
         todoToUpdate.setTodoDescription(todo.todoDescription());
         todoToUpdate.setDate(todo.date());
         todoToUpdate.setCompleted(todo.completed());
         todoRepository.save(todoToUpdate);
          TodoResponse todoResponse=new TodoResponse();
                  todoResponse.convertFromTodo(todoToUpdate);
                  return todoResponse;
     }
     else{
            return null;
     }
    }

    @Override
    public TodoResponse getTodoById(long id) {
        Optional<Todo> optTodo= todoRepository.findById(id);
        if(optTodo.isPresent()) {
            TodoResponse todoResponse=new TodoResponse();
            todoResponse.convertFromTodo(optTodo.get());
            return todoResponse;
        } else return null;
    }

    @Override
    public TodoResponse getTodoByName(String name) {
        Optional<Todo> optTodo= todoRepository.findByTodoName(name);
        if(optTodo.isPresent()) {
            TodoResponse todoResponse=new TodoResponse();
            todoResponse.convertFromTodo(optTodo.get());
            return todoResponse;
        } else return null;
    }

    @Override
    public List<TodoResponse> getAll() {
        List<Todo> todoList= todoRepository.findAllByUser_Id(getUser().getId());
        List<TodoResponse> todoResponseList = new ArrayList<>();
        todoList.forEach(todo->{
            TodoResponse todoResponse= new TodoResponse();
                   todoResponse.convertFromTodo(todo);
            todoResponseList.add(todoResponse);
        });

        return todoResponseList;
    }
    private User getUser(){
       Optional<User> user= userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

       if(user.isPresent()){
           return user.get();
       }else{
           throw new UsernameNotFoundException("User not found, todo cant be added.");
       }
    }
}
