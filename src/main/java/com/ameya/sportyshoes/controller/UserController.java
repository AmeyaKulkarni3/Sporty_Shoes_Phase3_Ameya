package com.ameya.sportyshoes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.exception.NoSuchUserException;
import com.ameya.sportyshoes.exception.UserAlreadyExistsException;
import com.ameya.sportyshoes.service.UserService;
import com.ameya.sportyshoes.utils.RoleEnum;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping("/register")
	public ModelAndView createUser(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		logger.info("email is " + email);

		UserDto user = new UserDto();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEncryptedPassword(password);
		user.setEmail(email);

		UserDto createdUser = userService.createUser(user);

		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("user", createdUser);
		mv.setViewName("registerationSuccess.jsp");

		return mv;
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws NoSuchUserException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDto user = new UserDto();
		user.setEmail(email);
		user.setEncryptedPassword(password);

		UserDto returnValue = userService.userLogin(user);

		ModelAndView mv = new ModelAndView();

		List<String> roles = returnValue.getRoles();
		if (roles.contains(RoleEnum.ROLE_ADMIN.toString())) {
			mv.addObject("admin", returnValue);
			mv.setViewName("adminMenu.jsp");
		} else {
			mv.addObject("user", returnValue);
			mv.setViewName("userMenu.jsp");
		}

		return mv;

	}
	
	@RequestMapping("/user")
	public ModelAndView getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		ModelAndView mv = new ModelAndView();
		mv.addObject("users",users);
		mv.setViewName("userHome.jsp");
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login.jsp");
		return mv;
	}
	
	@RequestMapping("/update-user")
	public ModelAndView updateUser(HttpServletRequest req, HttpServletResponse res) throws NoSuchUserException {
		UserDto dto = new UserDto();
		dto.setId(Integer.parseInt(req.getParameter("id")));
		dto.setFirstName(req.getParameter("fname"));
		dto.setLastName(req.getParameter("lname"));
		dto.setEmail(req.getParameter("email"));
		dto.setBalance(Double.parseDouble(req.getParameter("balance")));
		String r = req.getParameter("role");
		List<String> roles = new ArrayList<>();
		roles.add(r);
		dto.setRoles(roles);
		userService.updateUser(dto);
		return getAllUsers();
	}
	
	@RequestMapping("/delete-user")
	public ModelAndView deleteUser(HttpServletRequest req, HttpServletResponse res) throws NoSuchUserException {
		int id = Integer.parseInt(req.getParameter("id"));
		userService.deleteUser(id);
		return getAllUsers();
	}
	
	@RequestMapping("/search-user-by-name")
	public ModelAndView getUserByName(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("fname");
		List<UserDto> users = userService.getUserByName(name);
		ModelAndView mv = new ModelAndView();
		mv.addObject("usersAdmin",users);
		mv.setViewName("userList.jsp");
		return mv;
	}

}
