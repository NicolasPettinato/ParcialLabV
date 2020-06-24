package edu.utn.utnphones.controller.web;


import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.controller.UserController;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.CallDto;
import edu.utn.utnphones.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class AddUserController {

    private UserController userController;

    @Autowired
    public AddUserController(UserController userController) {
        this.userController = userController;
    }

    @PostMapping
    public User addUser(@RequestBody User user) throws UserAlreadyExistsException {
        return userController.createUser(user);
    }
}
