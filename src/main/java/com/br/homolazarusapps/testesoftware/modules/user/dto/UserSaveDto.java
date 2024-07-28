package com.br.homolazarusapps.testesoftware.modules.user.dto;

import jakarta.validation.constraints.Email;

public record UserSaveDto(
    Integer id,
    String name,
    @Email String email,
    String password
) {
    
}
