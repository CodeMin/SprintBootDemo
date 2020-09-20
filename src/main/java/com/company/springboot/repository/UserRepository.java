package com.company.springboot.repository;

import com.company.springboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> getById(Long id);

  UserEntity save(UserEntity userEntity);

}
