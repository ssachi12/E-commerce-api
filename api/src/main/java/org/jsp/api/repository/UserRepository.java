package org.jsp.api.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Product;
import org.jsp.api.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select m from User m where m.phone=?1 and m.password=?2")
	public Optional<User> verifyByPhone(long phone, String password);

	@Query("select m from User m where m.email=?1 and m.password=?2")
	public Optional<User> verifyByEmail(String email, String password);

	@Query("select m from User m where m.token=?1")
	public User findUserByToken(String token);

	@Query("select m from User m where m.email=?1")
	public User findUserByEmail(String email);
	
	@Query("select u.cart from User u where u.id=?1")
	public Optional<List<Product>> findByuserId(int id);
	
	@Query("select u.wishlist from User u where u.id=?1")
	public Optional<List<Product>> findWishByuserId(int id);
}
