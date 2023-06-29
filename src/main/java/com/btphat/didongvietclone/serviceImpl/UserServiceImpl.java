package com.btphat.didongvietclone.serviceImpl;

import com.btphat.didongvietclone.common.DateCommon;
import com.btphat.didongvietclone.dto.user.UserDto;
import com.btphat.didongvietclone.dto.user.UserDtoMapper;
import com.btphat.didongvietclone.entity.user.User;
import com.btphat.didongvietclone.entity.user.UserRoles;
import com.btphat.didongvietclone.exception.ResourceNotFoundException;
import com.btphat.didongvietclone.repository.UserRepository;
import com.btphat.didongvietclone.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserDtoMapper userDtoMapper;

    public UserServiceImpl(UserRepository repository,
                           UserDtoMapper userDtoMapper) {
        this.repository = repository;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public List<UserDto> findAllUsers() {

        List<User> users = repository.findAll();

        List<UserDto> userDtos = users.stream().map(userDtoMapper).collect(Collectors.toList());



        return userDtos;

    }

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!!!"));
    }

    @Override
    public User saveUser(UserDto dto) {

//        User user = convertUserDtoToUser(dto);

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder(dto.getPassword()));
        user.setRoles(UserRoles.ADMIN);
        user.setFullName(dto.getFullName());

        return repository.save(user);
    }

    @Override
    public User updateUser(UserDto dto) {

        User user = convertUserDtoToUser(dto);

        return repository.save(user);
    }

    @Override
    public boolean deleteUser(UserDto dto) {
        return false;
    }

    private User convertUserDtoToUser(UserDto dto) {

        return new User(
                dto.getId(),
                dto.getUsername(),
                encoder(dto.getPassword()),
                dto.getFullName(),
                UserRoles.valueOf("CUSTOMER")
        );
    }

    private String encoder(String password) {

        return new BCryptPasswordEncoder().encode(password);
    }
}
