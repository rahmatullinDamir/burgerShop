package org.example.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
}
