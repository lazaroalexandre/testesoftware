package com.br.homolazarusapps.testesoftware.modules.user.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.homolazarusapps.testesoftware.core.exceptions.DomainException;
import com.br.homolazarusapps.testesoftware.modules.user.dto.UserDetailDto;
import com.br.homolazarusapps.testesoftware.modules.user.dto.UserSaveDto;
import com.br.homolazarusapps.testesoftware.modules.user.dto.mapper.UserMapper;
import com.br.homolazarusapps.testesoftware.modules.user.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private UserMapper mapper;

    private void emailInUse(UserSaveDto model, Integer id) {
        Optional<UserSaveDto> userSaveDto = repository.findByEmail(model.email()).map(map -> mapper.toSaveDto(map));
        if (userSaveDto.isPresent() && !userSaveDto.get().id().equals(id)) {
            throw new DomainException("O e-mail já está em uso! Tente novamente.");
        }
    }

    @Transactional
    @Override
    public UserDetailDto detail(Integer id) {
        return repository.findById(id).map(map -> mapper.toDetailDto(map))
                .orElseThrow(() -> new DomainException("Usuário não encontrado!"));
    }

    @Transactional
    @Override
    public List<UserDetailDto> list() {
        return repository.findAll().stream().map(map -> mapper.toDetailDto(map)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDetailDto save(UserSaveDto model) {
        emailInUse(model, null);
        return mapper.toDetailDto(repository.save(mapper.toEntity(model)));
    }

    @Override
    public UserDetailDto update(UserSaveDto userSaveDto, Integer id) {
        return repository.findById(id).map(map -> {
            if (userSaveDto.email() != null) {
                emailInUse(userSaveDto, map.getId());
                map.setEmail(userSaveDto.email());
            }
            if (userSaveDto.name() != null) {
                map.setName(userSaveDto.name());
            }
            if (userSaveDto.password() != null) {
                map.setPassword(userSaveDto.password());
            }
            return repository.save(map);
        }).map(map -> mapper.toDetailDto(map)).orElseThrow(() -> new DomainException("Usuário não encontrado!"));
    }

    @Override
    public void delete(Integer id) {
        boolean modelPresent = repository.findById(id).isPresent();
        if (!modelPresent) {
            throw new DomainException("Usuário não encontrado!");
        }
        repository.deleteById(id);

    }
}
