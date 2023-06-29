package com.btphat.didongvietclone.controller;

import com.btphat.didongvietclone.dto.user.UserDto;
import com.btphat.didongvietclone.entity.user.User;
import com.btphat.didongvietclone.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> showAllUsers() {

        return this.userService.findAllUsers();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody UserDto dto) {

        return this.userService.saveUser(dto);
    }
}
