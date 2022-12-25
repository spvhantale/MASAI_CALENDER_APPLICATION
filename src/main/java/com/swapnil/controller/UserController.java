package com.swapnil.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.dto.LoginDTO;
import com.swapnil.dto.UserDTO;
import com.swapnil.exception.EventException;
import com.swapnil.exception.UserException;
import com.swapnil.model.Event;
import com.swapnil.model.User;
import com.swapnil.service.UserService;

@RestController
@RequestMapping("/masaicalender")
public class UserController {

	@Autowired
	private UserService uService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user)throws UserException{
		
		User u=uService.registerUser(user);
		
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO user)throws UserException{
		
		User u=uService.updateUser(user);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO login){
		
		String s=uService.loginUser(login);
		
		return new ResponseEntity<String>(s, HttpStatus.CREATED);
	}
	
	@GetMapping("/event/{email}/{type}")
	public ResponseEntity<List<Event>> getEvent(@PathVariable("type") String type,@PathVariable("email") String email) throws EventException, UserException{
		
		List<Event> elist=uService.getEvent(type, email);
		
		return new ResponseEntity<List<Event>>(elist, HttpStatus.OK);
	}
	
}
