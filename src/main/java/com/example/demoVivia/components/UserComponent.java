package com.example.demoVivia.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demoVivia.bos.RoleBo;
import com.example.demoVivia.bos.UserBo;
import com.example.demoVivia.bos.UserRoleBo;
import com.example.demoVivia.services.RoleService;
import com.example.demoVivia.services.UserRoleService;
import com.example.demoVivia.services.UserService;

@Component
public class UserComponent {

	@Autowired
	private UserService service;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;

	/**
	 * First find user entity using id. Then find list of role ids using userId Then
	 * find list of roles corresponding to the roleIds.
	 * 
	 * @param id
	 * @return
	 */
	public UserBo getUserById(Integer id) {

		UserBo userBo = service.findById(id);
		List<UserRoleBo> userRoleBos = userRoleService.findByUserId(id);
		List<Integer> roleIds = userRoleBos.stream().map(userRoleBo -> userRoleBo.getRoleId())
				.collect(Collectors.toList());
		
		
		List<RoleBo> roleBos = roleService.findListOfRoles(roleIds);
		userBo.setRoleBos(roleBos);
		return userBo;

	}

}
