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

import com.example.demoVivia.dtos.UserDto;
import com.example.demoVivia.entities.UserEntity;
import com.example.demoVivia.repositories.UserRepo;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserEntity entity = new UserEntity();
		entity.setName(userDto.getName());
		entity.setDepartment(userDto.getDepartment());
		userRepo.save(entity);

		userDto.setId(entity.getId());
		userDto.setName(entity.getName());
		userDto.setDepartment(entity.getDepartment());

		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId) {

		Optional<UserEntity> optionalEntity = userRepo.findById(userId);
		if (optionalEntity.isPresent()) {

			UserDto userDto = new UserDto();
			UserEntity entity = optionalEntity.get();
			userDto.setId(entity.getId());
			userDto.setName(entity.getName());
			userDto.setDepartment(entity.getDepartment());

			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {

		List<UserEntity> userEntities = userRepo.findAll();

		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (UserEntity entity : userEntities) {
			UserDto userDto = new UserDto();
			userDto.setId(entity.getId());
			userDto.setName(entity.getName());
			userDto.setDepartment(entity.getDepartment());
			userDtos.add(userDto);
		}

		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);

	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDto userDto) {

		userDto.setId(userId);
		UserEntity entity = new UserEntity();
		entity.setId(userDto.getId());
		entity.setName(userDto.getName());
		entity.setDepartment(userDto.getDepartment());
		userRepo.save(entity);

		userDto.setId(entity.getId());
		userDto.setName(entity.getName());
		userDto.setDepartment(entity.getDepartment());

		return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UserDto> updateUserPatch(@PathVariable("userId") Integer userId, @RequestBody UserDto userDto) {
		
		Optional<UserEntity> optionalEntity = userRepo.findById(userId);
		//
		
		if(optionalEntity.isPresent()) { 
			UserEntity entity = optionalEntity.get();
			if (userDto.getName() != null) {
				entity.setName(userDto.getName());
			}
			
			if (userDto.getDepartment() != null) {
				entity.setDepartment(userDto.getDepartment());
			}
			userRepo.save(entity);
			
			userDto.setId(entity.getId());
			userDto.setName(entity.getName());
			userDto.setDepartment(entity.getDepartment());
			
			return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
		}
		else 
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Integer userId) {
		userRepo.deleteById(userId);
		return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}

}
