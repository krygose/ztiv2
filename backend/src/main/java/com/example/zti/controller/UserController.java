package com.example.zti.controller;

import com.example.zti.service.user.UserService;
import com.example.zti.service.user.model.NewUserDto;
import com.example.zti.service.user.model.UserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE,
            path = "/register")
    @ResponseStatus(CREATED)
    public NewUserDto registerUser(@RequestBody @Valid NewUserDto newUserDto) {
        return userService.registerUser(newUserDto);
    }

    @GetMapping("/user")
    public UserDto getLoggedUser(Principal principal) {
        return userService.getUserByEmail(principal);
    }
}
