package com.ameya.sportyshoes.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDto implements Serializable{

	private static final long serialVersionUID = 5961153906407203023L;

	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private String encryptedPassword;

	private double balance;

	private List<String> roles;

	private List<OrderDto> orders;
	
}
