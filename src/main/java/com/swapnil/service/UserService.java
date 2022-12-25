package com.swapnil.service;

import java.util.List;


import com.swapnil.dto.LoginDTO;
import com.swapnil.dto.UserDTO;
import com.swapnil.exception.EventException;
import com.swapnil.exception.UserException;
import com.swapnil.model.Event;
import com.swapnil.model.MyUser;
import com.swapnil.model.User;

public interface UserService {

	
	public User registerUser(User user)throws UserException;
	public User updateUser(UserDTO user) throws UserException;
	
	public String  loginUser(LoginDTO login);

	public List<Event> getEvent(String type,String email)throws EventException,UserException;
}
