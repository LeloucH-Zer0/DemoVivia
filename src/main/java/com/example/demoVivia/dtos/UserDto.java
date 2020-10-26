package com.example.demoVivia.dtos;

import java.util.List;

import com.example.demoVivia.models.UserModel;

public class UserDto extends UserModel {
	
	private List<RoleDto> roleDtos;

	public List<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(List<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}
	
}
