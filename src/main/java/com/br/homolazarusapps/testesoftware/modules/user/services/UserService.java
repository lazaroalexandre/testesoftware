package com.br.homolazarusapps.testesoftware.modules.user.services;

import java.util.List;

import com.br.homolazarusapps.testesoftware.modules.user.dto.UserDetailDto;
import com.br.homolazarusapps.testesoftware.modules.user.dto.UserSaveDto;

public interface UserService {
    UserDetailDto detail(Integer id);
    List<UserDetailDto> list();
    UserDetailDto save(UserSaveDto userModel);
    UserDetailDto update(UserSaveDto userSaveDto, Integer id);
    void delete(Integer id);
}
