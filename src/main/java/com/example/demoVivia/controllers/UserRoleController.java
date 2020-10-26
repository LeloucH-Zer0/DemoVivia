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

import com.example.demoVivia.dtos.UserRoleDto;
import com.example.demoVivia.entities.UserRoleEntity;
import com.example.demoVivia.repositories.UserRoleRepo;

@RestController
@RequestMapping("/user_role")
public class UserRoleController {
	
	@Autowired
	private UserRoleRepo userroleRepo;
	
	@PostMapping
	public ResponseEntity<UserRoleDto> createUserRole(@RequestBody UserRoleDto userroleDto) {
		UserRoleEntity entity = new UserRoleEntity();
		entity.setUserId(userroleDto.getUserId());
		entity.setRoleId(userroleDto.getRoleId());
		userroleRepo.save(entity);

		userroleDto.setId(entity.getId());
		userroleDto.setUserId(entity.getUserId());
		userroleDto.setRoleId(entity.getRoleId());

		return new ResponseEntity<UserRoleDto>(userroleDto, HttpStatus.CREATED);
	}

	@GetMapping("/{userroleId}")
	public ResponseEntity<UserRoleDto> getUserRole(@PathVariable("userroleId") Integer userroleId) {

		Optional<UserRoleEntity> optionalEntity = userroleRepo.findById(userroleId);
		if (optionalEntity.isPresent()) {

			UserRoleDto userroleDto = new UserRoleDto();
			UserRoleEntity entity = optionalEntity.get();
			userroleDto.setId(entity.getId());
			userroleDto.setUserId(entity.getUserId());
			userroleDto.setRoleId(entity.getRoleId());
			userroleDto.setStatus(entity.getStatus());

			return new ResponseEntity<UserRoleDto>(userroleDto, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<UserRoleDto>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping
	public ResponseEntity<List<UserRoleDto>> getUserRoles() {

		List<UserRoleEntity> userroleEntities = userroleRepo.findAll();

		List<UserRoleDto> userroleDtos = new ArrayList<UserRoleDto>();
		for (UserRoleEntity entity : userroleEntities) {
			UserRoleDto userroleDto = new UserRoleDto();
			userroleDto.setId(entity.getId());
			userroleDto.setUserId(entity.getUserId());
			userroleDto.setRoleId(entity.getRoleId());
			userroleDtos.add(userroleDto);
		}

		return new ResponseEntity<List<UserRoleDto>>(userroleDtos, HttpStatus.OK);

	}
	
	@PutMapping("/{userroleId}")
	public ResponseEntity<UserRoleDto> updateUserRole(@PathVariable("userroleId") Integer userroleId, @RequestBody UserRoleDto userroleDto) {

		userroleDto.setId(userroleId);
		UserRoleEntity entity = new UserRoleEntity();
		entity.setId(userroleDto.getId());
		entity.setUserId(userroleDto.getUserId());
		entity.setRoleId(userroleDto.getRoleId());
		userroleRepo.save(entity);

		userroleDto.setId(entity.getId());
		userroleDto.setUserId(entity.getUserId());
		userroleDto.setRoleId(entity.getRoleId());

		return new ResponseEntity<UserRoleDto>(userroleDto, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/{userroleId}")
	public ResponseEntity<UserRoleDto> updateUserRolePatch(@PathVariable("userroleId") Integer userroleId, @RequestBody UserRoleDto userroleDto) {
		
		Optional<UserRoleEntity> optionalEntity = userroleRepo.findById(userroleId);
		//
		
		if(optionalEntity.isPresent()) { 
			UserRoleEntity entity = optionalEntity.get();
			if (userroleDto.getUserId() != null) {
				entity.setUserId(userroleDto.getUserId());
			}
			
			if (userroleDto.getRoleId() != null) {
				entity.setRoleId(userroleDto.getRoleId());
			}
			userroleRepo.save(entity);
			
			userroleDto.setId(entity.getId());
			userroleDto.setUserId(entity.getUserId());
			userroleDto.setRoleId(entity.getRoleId());
			
			return new ResponseEntity<UserRoleDto>(userroleDto, HttpStatus.ACCEPTED);
		}
		else 
			return new ResponseEntity<UserRoleDto>(HttpStatus.NOT_FOUND);
		
	}
	
	/**
	 * user role id
	 * find record associated with user role id
	 * set status to false
	 * saving it to the DB
	 * @param userroleId
	 * @return
	 */
	@DeleteMapping("/{userroleId}")
	public ResponseEntity<UserRoleDto> deleteUserRole(@PathVariable("userroleId") Integer userroleId) {
		Optional<UserRoleEntity> userroleEntity = userroleRepo.findById(userroleId);
		UserRoleEntity entity = userroleEntity.get();
		
		entity.setStatus(false);
		
		userroleRepo.save(entity);
		return new ResponseEntity<UserRoleDto>(HttpStatus.NO_CONTENT);
	}

}
