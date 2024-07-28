package com.br.homolazarusapps.testesoftware.modules.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.homolazarusapps.testesoftware.modules.user.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
    Optional<UserModel> findByEmail(String email);
}
