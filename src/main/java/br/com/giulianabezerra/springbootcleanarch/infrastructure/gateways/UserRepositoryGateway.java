package br.com.giulianabezerra.springbootcleanarch.infrastructure.gateways;

import br.com.giulianabezerra.springbootcleanarch.application.gateways.UserGateway;
import br.com.giulianabezerra.springbootcleanarch.domain.entity.User;
import br.com.giulianabezerra.springbootcleanarch.infrastructure.persistence.UserEntity;
import br.com.giulianabezerra.springbootcleanarch.infrastructure.persistence.UserRepository;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    /* Construtor */
    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User userDomainObj) {
        /*
            Faz a chamada da Repository
            OBS: return userRepository.save(user); -> não pode ser dessa forma pq aqui eu estou usando a entidade de domínio e a repository espera a entidade mapeada do banco
            1 - Criar uma UserEntity a partir de uma entidade de domínio
            2 - Para facilitar criar uma classe Mapper para realizar a conversão
            3 - Criar a Entidade
            4 - Recebe objeto de domínio e devolve objeto de domínio
        */

        UserEntity userEntity = userEntityMapper.toEntity(userDomainObj);
        UserEntity saveObj = userRepository.save(userEntity);
        return userEntityMapper.toDomainObj(saveObj);
    }
}
