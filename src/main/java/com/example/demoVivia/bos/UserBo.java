package com.example.demoVivia.bos;

import java.util.List;

import com.example.demoVivia.models.UserModel;

public class UserBo extends UserModel {
	
	private List<RoleBo> roleBos;

	public List<RoleBo> getRoleBos() {
		return roleBos;
	}

	public void setRoleBos(List<RoleBo> roleBos) {
		this.roleBos = roleBos;
	}
	
}
