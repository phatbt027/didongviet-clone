package com.btphat.didongvietclone.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;

    private String username;

    private String password;

    private String fullName;

    private String role;

}
