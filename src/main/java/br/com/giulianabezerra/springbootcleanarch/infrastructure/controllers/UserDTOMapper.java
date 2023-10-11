package br.com.giulianabezerra.springbootcleanarch.infrastructure.controllers;

import br.com.giulianabezerra.springbootcleanarch.domain.entity.User;

public class UserDTOMapper {
    CreateUserResponse toResponse(User user) {
        /* Pega obj de domínio e converte em uma resposta */
        return new CreateUserResponse(user.username(), user.email());
    }

    public User toUser(CreateUserRequest request) {
        /* pega uma request e converte em obj de domínio */
        return new User(request.username(), request.password(), request.email());
    }

}
