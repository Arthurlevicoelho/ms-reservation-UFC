package com.arthurlevi.msuser.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotBlank(message = "Email not null") @Email String email,
                            @NotBlank(message = "password not null") String password,
                            @NotNull(message = "registration not null") Integer registration) {
}
