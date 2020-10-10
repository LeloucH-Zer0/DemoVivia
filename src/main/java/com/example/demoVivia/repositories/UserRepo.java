package com.example.demoVivia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoVivia.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
