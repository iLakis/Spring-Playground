package com.learning.course.controller;

import com.learning.course.entity.UserEntity;
import com.learning.course.exceptions.UserAlreadyExistsException;
import com.learning.course.exceptions.UserNotFoundException;
import com.learning.course.repository.UserRepo;
import com.learning.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return  ResponseEntity.ok("Пользователь сохранён");
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }



    @GetMapping()
    public ResponseEntity getUsers(@RequestParam Long id){
        try {
            return  ResponseEntity.ok(userService.getOne(id));
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return  ResponseEntity.ok(userService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
