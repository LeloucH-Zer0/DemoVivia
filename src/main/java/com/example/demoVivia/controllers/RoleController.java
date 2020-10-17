package com.example.demoVivia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoVivia.dtos.RoleDto;
import com.example.demoVivia.dtos.UserDto;
import com.example.demoVivia.entities.RoleEntity;
import com.example.demoVivia.entities.UserEntity;
import com.example.demoVivia.repositories.RoleRepo;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@PostMapping
	public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
		RoleEntity entity = new RoleEntity();
		entity.setName(roleDto.getName());
		roleRepo.save(entity);

		roleDto.setId(entity.getId());
		roleDto.setName(entity.getName());

		return new ResponseEntity<RoleDto>(roleDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/{roleId}")
	public ResponseEntity<RoleDto> getRole(@PathVariable("roleId") Integer roleId) {

		Optional<RoleEntity> optionalEntity = roleRepo.findById(roleId);
		if (optionalEntity.isPresent()) {

			RoleDto roleDto = new RoleDto();
			RoleEntity entity = optionalEntity.get();
			roleDto.setId(entity.getId());
			roleDto.setName(entity.getName());

			return new ResponseEntity<RoleDto>(roleDto, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<RoleDto>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping
	public ResponseEntity<List<RoleDto>> getRoles() {

		List<RoleEntity> roleEntities = roleRepo.findAll();

		List<RoleDto> roleDtos = new ArrayList<RoleDto>();
		for (RoleEntity entity : roleEntities) {
			RoleDto roleDto = new RoleDto();
			roleDto.setId(entity.getId());
			roleDto.setName(entity.getName());
			roleDtos.add(roleDto);
		}

		return new ResponseEntity<List<RoleDto>>(roleDtos, HttpStatus.OK);

	}

	@PutMapping("/{roleId}")
	public ResponseEntity<RoleDto> updateRole(@PathVariable("roleId") Integer roleId, @RequestBody RoleDto roleDto) {

		roleDto.setId(roleId);
		RoleEntity entity = new RoleEntity();
		entity.setId(roleDto.getId());
		entity.setName(roleDto.getName());
		roleRepo.save(entity);

		roleDto.setId(entity.getId());
		roleDto.setName(entity.getName());

		return new ResponseEntity<RoleDto>(roleDto, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/{roleId}")
	public ResponseEntity<RoleDto> updateRolePatch(@PathVariable("roleId") Integer roleId, @RequestBody RoleDto roleDto) {
		
		Optional<RoleEntity> optionalEntity = roleRepo.findById(roleId);
		//
		
		if(optionalEntity.isPresent()) { 
			RoleEntity entity = optionalEntity.get();
			if (roleDto.getName() != null) {
				entity.setName(roleDto.getName());
			}
			
			roleRepo.save(entity);
			
			roleDto.setId(entity.getId());
			roleDto.setName(entity.getName());
			
			return new ResponseEntity<RoleDto>(roleDto, HttpStatus.ACCEPTED);
		}
		else 
			return new ResponseEntity<RoleDto>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{roleId}")
	public ResponseEntity<RoleDto> deleteRole(@PathVariable("roleId") Integer roleId) {
		roleRepo.deleteById(roleId);
		return new ResponseEntity<RoleDto>(HttpStatus.NO_CONTENT);
	}


}
