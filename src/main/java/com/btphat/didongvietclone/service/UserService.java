package com.btphat.didongvietclone.service;

import com.btphat.didongvietclone.dto.user.UserDto;
import com.btphat.didongvietclone.entity.user.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAllUsers();

    User findUserByUsername(String username);

    User saveUser(UserDto dto);

    User updateUser(UserDto dto);

    boolean deleteUser(UserDto dto);
}
