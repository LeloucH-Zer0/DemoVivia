package com.example.demoVivia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoVivia.bos.UserBo;
import com.example.demoVivia.entities.UserEntity;
import com.example.demoVivia.repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;

	public UserBo findById(Integer id) {
		Optional<UserEntity> entity = repo.findById(id);
		UserBo bo = new UserBo();
		if (!entity.isPresent()) {
			//TODO throw error here.
		}
		else {
			bo.setDepartment(entity.get().getDepartment());
			bo.setId(entity.get().getId());
			bo.setName(entity.get().getName());
		}
		return bo;
	}
}
