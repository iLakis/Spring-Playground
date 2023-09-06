package com.learning.course.service;

import com.learning.course.entity.TodoEntity;
import com.learning.course.entity.UserEntity;
import com.learning.course.model.Todo;
import com.learning.course.repository.TodoRepo;
import com.learning.course.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public Todo createTodo(TodoEntity todo, Long id){
        UserEntity user = userRepo.findById(id).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }
    public Todo completeTodo(Long id){
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}
