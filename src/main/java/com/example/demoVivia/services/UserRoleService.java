package com.example.demoVivia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoVivia.bos.UserRoleBo;
import com.example.demoVivia.entities.UserRoleEntity;
import com.example.demoVivia.repositories.UserRoleRepo;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepo repo;

	public List<UserRoleBo> findByUserId(Integer userId) {
		List<UserRoleEntity> userRoleEntities = repo.findByUserId(userId);
		List<UserRoleBo> userRoleBos = new ArrayList<>();
		for (UserRoleEntity userRoleEntity : userRoleEntities) {
			UserRoleBo bo = new UserRoleBo();
			bo.setId(userRoleEntity.getId());
			bo.setRoleId(userRoleEntity.getRoleId());
			bo.setUserId(userRoleEntity.getUserId());
			userRoleBos.add(bo);
		}
		return userRoleBos;
	}

}
