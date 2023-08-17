package org.jsp.api.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Product;
import org.jsp.api.dto.User;
import org.jsp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}
	public User updateUser(User user) {
		return repository.save(user);
	}
	public Optional<User> findUserById(int id) {
		return repository.findById(id);
	}
	public void deleteUser(int id){
		 repository.deleteById(id);
	}
	public Optional<User> verifyByPhone(long phone,String password){
		return repository.verifyByPhone(phone, password);
	}
	public Optional<User> verifyByEmail(String email,String password){
		return repository.verifyByEmail(email, password);
	}
	public User activeUser(String token) {
		return repository.findUserByToken(token);
	}
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findUserByEmail(email);
	}
	public Optional<List<Product>> findByCartUserId(int id){
		return repository.findByuserId(id);
	}
	public Optional<List<Product>> findWishByUserId(int id){
		return repository.findWishByuserId(id);
	}
}
