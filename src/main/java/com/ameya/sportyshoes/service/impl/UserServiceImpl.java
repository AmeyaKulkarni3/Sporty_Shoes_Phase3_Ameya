package com.ameya.sportyshoes.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.ameya.sportyshoes.dto.OrderDto;
import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.entity.Order;
import com.ameya.sportyshoes.entity.Role;
import com.ameya.sportyshoes.entity.User;
import com.ameya.sportyshoes.exception.ExceptionConstants;
import com.ameya.sportyshoes.exception.NoSuchUserException;
import com.ameya.sportyshoes.exception.UserAlreadyExistsException;
import com.ameya.sportyshoes.repository.RoleRepository;
import com.ameya.sportyshoes.repository.UserRepository;
import com.ameya.sportyshoes.service.UserService;
import com.ameya.sportyshoes.utils.RoleEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:ValidationMessages.properties")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	Environment env;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDto createUser(UserDto user) throws Exception {

		User saved = userRepository.findByEmail(user.getEmail());
		if (saved != null) {
			throw new UserAlreadyExistsException(env.getProperty(ExceptionConstants.USER_ALREADY_EXISTS.toString()));
		}
		User userEntity = new User();
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setEncryptedPassword(user.getEncryptedPassword());
		userEntity.setEmail(user.getEmail());
		userEntity.setBalance(100000);
		Role r = roleRepository.findByName(RoleEnum.ROLE_USER.name());
		List<Role> roles = new ArrayList<>();
		roles.add(r);
		userEntity.setRoles(roles);
		User created = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		if (created != null) {
			returnValue.setId(created.getId());
			returnValue.setFirstName(created.getFirstName());
			returnValue.setLastName(created.getLastName());
			returnValue.setEmail(created.getEmail());
			List<Role> rolesSaved = (List<Role>) created.getRoles();
			List<String> rs = new ArrayList<>();
			for (Role role : rolesSaved) {
				rs.add(role.getName());
			}
			returnValue.setRoles(rs);
		} else {
			throw new Exception(env.getProperty(ExceptionConstants.GENERAL_EXCEPTION.toString()));
		}
		return returnValue;
	}

	@Override
	public UserDto userLogin(UserDto user) throws NoSuchUserException {

		User saved = userRepository.findByEmail(user.getEmail());
		UserDto returnValue = new UserDto();
		if (saved != null) {
			if (saved.getEncryptedPassword().equals(user.getEncryptedPassword())) {

				returnValue.setFirstName(saved.getFirstName());
				returnValue.setLastName(saved.getLastName());
				returnValue.setEmail(saved.getEmail());
				returnValue.setId(saved.getId());
				returnValue.setBalance(saved.getBalance());
				List<Order> orders = saved.getOrders();
				List<OrderDto> orderDtos = new ArrayList<>();
				for (Order o : orders) {
					OrderDto dto = new OrderDto();
					dto.setId(o.getId());
					dto.setDate(o.getDate());
					dto.setTotalAmount(o.getTotalAmount());
					orderDtos.add(dto);
				}
				returnValue.setOrders(orderDtos);
				List<Role> roles = (List<Role>) saved.getRoles();
				List<String> userRoles = new ArrayList<>();
				for (Role r : roles) {
					userRoles.add(r.getName());
				}
				returnValue.setRoles(userRoles);
			}
		} else {
			throw new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString()));
		}
		return returnValue;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = (List<User>) userRepository.findAll();
		List<UserDto> dtos = new ArrayList<>();
		for (User user : users) {
			dtos.add(dataTranfer(user));
		}
		return dtos;
	}

	private UserDto dataTranfer(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		dto.setBalance(user.getBalance());
		List<String> r = new ArrayList<>();
		for (Role role : user.getRoles()) {
			r.add(role.getName());
		}
		dto.setRoles(r);
		return dto;
	}

	@Override
	public UserDto getUserById(int id) throws NoSuchUserException {
		User user = userRepository.findById(id).orElseThrow(
				() -> new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString())));
		UserDto dto = dataTranfer(user);
		return dto;
	}

	@Override
	public void updateUser(UserDto dto) throws NoSuchUserException {
		User user = userRepository.findById(dto.getId()).orElseThrow(
				() -> new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString())));
		if (!user.getFirstName().equals(dto.getFirstName())) {
			user.setFirstName(dto.getFirstName());
		}
		if (!user.getLastName().equals(dto.getLastName())) {
			user.setLastName(dto.getLastName());
		}
		if (!user.getEmail().equals(dto.getEmail())) {
			user.setEmail(dto.getEmail());
		}
		if (user.getBalance() != dto.getBalance()) {
			user.setBalance(dto.getBalance());
		}
		List<Role> savedRoles = (List<Role>) user.getRoles();
		if (!savedRoles.get(0).equals(dto.getRoles().get(0))) {
			Role r = roleRepository.findByName(dto.getRoles().get(0));
			Collection<Role> rs = user.getRoles();
			rs.removeAll(user.getRoles());
			rs.add(r);
			user.setRoles(rs);
		}
		userRepository.save(user);

	}

	@Override
	public void deleteUser(int id) throws NoSuchUserException {
		User user = userRepository.findById(id).orElseThrow(
				() -> new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString())));
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> getUserByName(String name) {
		List<User> users = userRepository.findAllByFirstName(name);
		List<UserDto> returnValue = new ArrayList<>();
		for(User user : users) {
			UserDto dto = new UserDto();
			dto = dataTranfer(user);
			returnValue.add(dto);
		}
		return returnValue;
	}

}
