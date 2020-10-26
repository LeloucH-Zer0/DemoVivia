package com.example.demoVivia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoVivia.bos.RoleBo;
import com.example.demoVivia.entities.RoleEntity;
import com.example.demoVivia.repositories.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo repo;
	
	public List<RoleBo> findListOfRoles(List<Integer> roleIds) {
		List<RoleEntity> roleEntities = repo.findAllById(roleIds);
		List<RoleBo> roleBos = new ArrayList<RoleBo>();
		for (RoleEntity roleEntity: roleEntities) {
			RoleBo roleBo = new RoleBo();
			roleBo.setId(roleEntity.getId());
			roleBo.setName(roleEntity.getName());
			roleBos.add(roleBo);
		}
		return roleBos;
	}
}
