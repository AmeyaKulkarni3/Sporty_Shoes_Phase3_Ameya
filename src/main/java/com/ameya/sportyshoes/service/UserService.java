package com.ameya.sportyshoes.service;

import java.util.List;

import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.exception.NoSuchUserException;
import com.ameya.sportyshoes.exception.UserAlreadyExistsException;

public interface UserService {
	
	public UserDto createUser(UserDto user) throws UserAlreadyExistsException, Exception;
	
	public UserDto userLogin(UserDto user) throws NoSuchUserException;

	public List<UserDto> getAllUsers();

	public UserDto getUserById(int id) throws NoSuchUserException;

	public void updateUser(UserDto dto) throws NoSuchUserException;

	public void deleteUser(int id) throws NoSuchUserException;

	public List<UserDto> getUserByName(String name);
	
	public void updateUserByUser(UserDto dto) throws NoSuchUserException;

}
