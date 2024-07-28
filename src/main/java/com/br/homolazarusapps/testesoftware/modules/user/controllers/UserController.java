package com.br.homolazarusapps.testesoftware.modules.user.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.homolazarusapps.testesoftware.modules.user.dto.UserDetailDto;
import com.br.homolazarusapps.testesoftware.modules.user.dto.UserSaveDto;
import com.br.homolazarusapps.testesoftware.modules.user.services.UserService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping("/{id}")
    public UserDetailDto findById(@PathVariable Integer id) {
        return service.detail(id);
    }

    @GetMapping()
    public List<UserDetailDto> findAll() {
        return service.list();
    }

    @PostMapping()
    public UserDetailDto postOne(@RequestBody UserSaveDto saveDto) {
        return service.save(saveDto);
    }

    @PatchMapping("/{id}")
    public UserDetailDto patchOneById(@RequestBody UserSaveDto saveDto, @PathVariable Integer id) {
        return service.update(saveDto, id);
    }

    @DeleteMapping("/{id}")
    public void removeOneById(@PathVariable Integer id) {
        service.delete(id);
    }
}
