package com.provider.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @NotNull
    @Size(min = 2, max = 30)
    private String login;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 30)
    private String password;
}
