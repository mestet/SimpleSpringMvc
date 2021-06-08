package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String password;
    private UserRole role;

    public enum UserRole {
        ADMIN, COMMON
    }
}