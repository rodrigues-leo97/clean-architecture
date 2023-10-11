package br.com.giulianabezerra.springbootcleanarch.infrastructure.controllers;

import br.com.giulianabezerra.springbootcleanarch.application.usecases.CreateUserInteractor;
import br.com.giulianabezerra.springbootcleanarch.domain.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    /*
        1- No lugar da Service se usa o Interector
        2- Se estivessemos usando a INTERFACE ela seria colocada aqui, como não, colocamos direto a implementação

        OBJ -> Não passar s request que trás a assinatura da API para as camadas internas, eu mesmo farei a conversão
                As Camadas EXTERNAS podem depender do objeto de domínio, é as internas que não podem depender das externas
    */
    private final CreateUserInteractor createUserInteractor;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUserInteractor createUserInteractor, UserDTOMapper userDTOMapper) {
        this.createUserInteractor = createUserInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    CreateUserResponse create(@RequestBody CreateUserRequest createUserRequest) {
        User userBusinessObj = userDTOMapper.toUser(createUserRequest);
        User user = createUserInteractor.createUser(userBusinessObj); //converter essa request em um User e passar o obj de negócio
        return userDTOMapper.toResponse(user);
    }
}
