package com.br.homolazarusapps.testesoftware.modules.user.dto.mapper;

import org.springframework.stereotype.Component;

import com.br.homolazarusapps.testesoftware.modules.user.dto.UserDetailDto;
import com.br.homolazarusapps.testesoftware.modules.user.dto.UserSaveDto;
import com.br.homolazarusapps.testesoftware.modules.user.models.UserModel;

@Component
public class UserMapper {

    public UserDetailDto toDetailDto(UserModel model) {
        if (model == null) {
            return null;
        }
        return new UserDetailDto(model.getId(), model.getName(), model.getEmail());
    }

    public UserSaveDto toSaveDto(UserModel model) {
        if (model == null) {
            return null;
        }

        return new UserSaveDto(model.getId(), model.getName(), model.getEmail(), model.getPassword());
    }

    public UserModel toEntity(UserSaveDto saveDto){
        if (saveDto == null) {
            return null;
        }

        UserModel model = new UserModel(); 

        if (saveDto.id() != null) {
            model.setId(saveDto.id());
        }

        model.setName(saveDto.name());
        model.setEmail(saveDto.email());
        model.setPassword(saveDto.password());

        return model;
    }

}
