package com.example.demoVivia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoVivia.entities.RoleEntity;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer>{

}
