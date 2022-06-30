package com.ameya.sportyshoes.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.JoinColumn;

@Data
@Entity
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = 5961153906407203023L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	@Column
	private double balance;
	
	@Column(nullable=false)
	@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name="users_roles",
			joinColumns = @JoinColumn(name="users_id",referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="roles_id",referencedColumnName="id"))
	private Collection<Role> roles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders;
	
}
