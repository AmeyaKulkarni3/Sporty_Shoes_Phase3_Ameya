package com.ameya.sportyshoes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ameya.sportyshoes.entity.Authority;
import com.ameya.sportyshoes.entity.Role;
import com.ameya.sportyshoes.entity.User;
import com.ameya.sportyshoes.repository.*;
import com.ameya.sportyshoes.utils.RoleEnum;
import com.ameya.sportyshoes.utils.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialUsersSetup {

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Utilities utilities;
	
	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;

//	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {

		Authority readAuthority = createAuthority("READ_AUTHORITY");
		Authority writeAuthority = createAuthority("WRITE_AUTHORITY");
		Authority deleteAuthority = createAuthority("DELETE_AUTHORITY");

		createRole(RoleEnum.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
		Role roleAdmin = createRole(RoleEnum.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		
		if(roleAdmin == null) return;
		
		User user = new User();
		user.setFirstName("Admin");
		user.setLastName("Admin");
		user.setEmail("admin@email.com");
		user.setEncryptedPassword("admin");
		user.setRoles(Arrays.asList(roleAdmin));
		List<User> savedUsers = (List<User>) userRepository.findAll();
		if(savedUsers.size() == 0) {
			userRepository.save(user);
		}
	}

	@Transactional
	private Authority createAuthority(String name) {

		Authority authority = authorityRepository.findByName(name);

		if (authority == null) {
			authority = new Authority(name);
			authorityRepository.save(authority);
		}

		return authority;
	}

	@Transactional
	private Role createRole(String name, Collection<Authority> authoroties) {

		Role role = roleRepository.findByName(name);

		if (role == null) {
			role = new Role(name);
			role.setAuthorities(authoroties);
			roleRepository.save(role);
		}

		return role;

	}

}
