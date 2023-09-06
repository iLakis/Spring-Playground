package com.learning.course.service;

import com.learning.course.entity.UserEntity;
import com.learning.course.exceptions.UserAlreadyExistsException;
import com.learning.course.exceptions.UserNotFoundException;
import com.learning.course.model.User;
import com.learning.course.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if(userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        return  userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if(user == null) {
            throw new UserNotFoundException("Пользователь с таким ид не найден");
        }
        return User.toModel(user);
    }

    public Long delete(Long id){
        userRepo.deleteById(id);
        return id;
    }
}
