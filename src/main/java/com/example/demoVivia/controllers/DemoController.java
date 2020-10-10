package com.example.demoVivia.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class DemoController {
	
	@GetMapping
	public ResponseEntity<String> sayHello() {
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> sayWorld() {
		return new ResponseEntity<String>("World", HttpStatus.OK);
	}

}
	
