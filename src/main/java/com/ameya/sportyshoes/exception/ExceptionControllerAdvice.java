package com.ameya.sportyshoes.exception;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	private Environment environment;

	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandlerCustom(Exception ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;

	}

	@ExceptionHandler(NoSuchUserException.class)
	public ModelAndView exceptionHandlerCustom(NoSuchUserException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;
	}
	
	@ExceptionHandler(NotEnoughBalanceException.class)
	public ModelAndView exceptionHandlerCustom(NotEnoughBalanceException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;
	}

	@ExceptionHandler(CategoryAlreadyExistsException.class)
	public ModelAndView exceptionHandlerCustom(CategoryAlreadyExistsException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;

	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ModelAndView exceptionHandlerCustom(ProductAlreadyExistsException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;

	}

	@ExceptionHandler(NoSuchCategoryException.class)
	public ModelAndView exceptionHandlerCustom(NoSuchCategoryException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;

	}
	
	@ExceptionHandler(NoSuchProductException.class)
	public ModelAndView exceptionHandlerCustom(NoSuchProductException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;

	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ModelAndView exceptionHandlerCustom(UserAlreadyExistsException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("er", error);
		mv.setViewName("error.jsp");
		return mv;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(", ")));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(ConstraintViolationException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining(", ")));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
