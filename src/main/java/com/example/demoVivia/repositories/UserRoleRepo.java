package com.example.demoVivia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoVivia.entities.UserRoleEntity;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRoleEntity, Integer> {
	
	List<UserRoleEntity> findByUserIdAndStatus(Integer userId, Boolean status); 

}
