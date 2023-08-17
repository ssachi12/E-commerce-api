package org.jsp.api.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Product;
import org.jsp.api.dto.User;
import org.jsp.api.service.UserService;
import org.jsp.api.service.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user,HttpServletRequest request) {
		return service.saveUser(user, request);
	}

	@GetMapping("/user/active")
	public String activeUser(@RequestParam String token) {
		return service.activeUser(token);
	}

	@GetMapping("/user/reset")
	public String forgotPassword(@RequestParam String email, HttpServletRequest request) {
		return service.sendPasswordLink(email, request);
	}

	@PutMapping("/user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}

	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<User>> findUser(@PathVariable int id) {
		return service.findUserById(id);
	}

	@PostMapping("/user/login-ph")
	public ResponseEntity<ResponseStructure<User>> verifyUserByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return service.verifyUserByPhone(phone, password);
	}

	@PostMapping("/user/login-em")
	public ResponseEntity<ResponseStructure<User>> verifyUserByEmail(@RequestParam String email,
			@RequestParam String password) {
		return service.verifyUserByEmail(email, password);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<String>> DeleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}
	@GetMapping("/user/cart/{id}")
	public ResponseEntity<ResponseStructure<Optional<List<Product>>>> findCartByUserId(@PathVariable int id){
		return service.findCartByUserId(id); 
	}
	@GetMapping("/user/wish/{id}")
	public ResponseEntity<ResponseStructure<Optional<List<Product>>>> findWishByUserId(@PathVariable int id){
		return service.findWishByUserId(id); 
	}
}
