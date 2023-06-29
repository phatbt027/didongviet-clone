package com.btphat.didongvietclone.security;

import com.btphat.didongvietclone.entity.user.User;
import com.btphat.didongvietclone.exception.ResourceNotFoundException;
import com.btphat.didongvietclone.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!!!"));

        try {

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toString())
                    .build();

        }catch (UsernameNotFoundException ex) {

            throw new UsernameNotFoundException("Invail username or password!!!");
        }
    }
}
