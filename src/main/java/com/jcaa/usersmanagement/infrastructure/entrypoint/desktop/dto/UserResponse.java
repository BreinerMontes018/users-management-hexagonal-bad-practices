package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

// correccion regla 2 usar record para DTO inmutable
// correccion regla 15 evitar mutabilidad y setters publicos
public record UserResponse(

        String id,
        String name,
        String email,
        String role,
        String status) {
}