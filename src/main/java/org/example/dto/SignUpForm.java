package org.example.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpForm {
    private String username;
    private String password;
}
