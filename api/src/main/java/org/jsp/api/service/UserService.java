package org.jsp.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.jsp.api.dao.UserDao;
import org.jsp.api.dto.EmailConfigaration;
import org.jsp.api.dto.Product;
import org.jsp.api.dto.User;
import org.jsp.api.exception.CrNotFoundException;
import org.jsp.api.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private EmailConfigaration config;
	@Autowired
	private ShoppingCartMailService mailservice;
	@Autowired
	private GenerateUserLinkService linkService;
	@Autowired
	private GetUserResetPasswordLink resetService;
//save User
	public ResponseEntity<ResponseStructure<User>> saveUser(User user,HttpServletRequest request) {
		config.setSubject("Registration successfull");
		dao.saveUser(user);
		HashMap<String, String> map=new HashMap<>();
		map.put("email", user.getEmail());
		map.put("name", user.getName());
		config.setText("Hello Mr "+user.getName() 
		+" You have successfully initiated the registration for our e-commerce store"+
				"please click on the link "+linkService.getVerificationLinkUser(request, user));
		config.setUser(map);
		mailservice.sendWelcomeMail(config);
		ResponseStructure<User> s = new ResponseStructure<>();
		s.setMessage("User succcessfully saved");
		s.setBody(user);
		s.setCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(s, HttpStatus.CREATED);
	}
	public String sendPasswordLink(String email,HttpServletRequest request) {
		User user=dao.findUserByEmail(email);
		if(user!=null) {
			HashMap<String, String> map=new HashMap<>();
			map.put("email", user.getEmail());
			map.put("name", user.getName());
			config.setText(
			" You can change the password by below link"+
					"please click on the link "+resetService.resetPasswordLinkUser(request, user));
			config.setUser(map);
			mailservice.sendWelcomeMail(config);
			return "reset password link sent to email";
		}
		return "you have not registered";
	}

//	update User
	public ResponseEntity<ResponseStructure<User>> updateUser(User User) {
		ResponseStructure<User> s = new ResponseStructure<>();
		s.setMessage("User succcessfully updated");
		s.setBody(dao.updateUser(User));
		s.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(s, HttpStatus.ACCEPTED);
	}

//	find User by id
	public ResponseEntity<ResponseStructure<User>> findUserById(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.findUserById(id);
		if (recUser.isPresent()) {
			structure.setMessage("User found");
			structure.setBody(recUser.get());
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}

		throw new IdNotFoundException();

	}

//	verify User by phone number
	
	public ResponseEntity<ResponseStructure<User>> verifyUserByPhone(long phone, String password) {
		ResponseStructure<User> s = new ResponseStructure<>();
		Optional<User> recUser = dao.verifyByPhone(phone, password);
		if (recUser.isPresent()) {
			s.setBody(recUser.get());
			s.setMessage("User verified successfully");
			s.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(s, HttpStatus.OK);
		}

		throw new CrNotFoundException();

	}
//	User login by email
	public ResponseEntity<ResponseStructure<User>> verifyUserByEmail(String email, String password) {
		ResponseStructure<User> s = new ResponseStructure<>();
		Optional<User> recUser = dao.verifyByEmail(email, password);
		if (recUser.isPresent()) {
			s.setBody(recUser.get());
			s.setMessage("User verified successfully");
			s.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(s, HttpStatus.OK);
		}

		throw new CrNotFoundException();

	}
//	delete User
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		ResponseStructure<String> s = new ResponseStructure<>();
		Optional<User> recUser = dao.findUserById(id);
		if (recUser.isPresent()) {
			dao.deleteUser(id);
			s.setMessage("User found");
			s.setBody("User deleted suceesfully");
			s.setCode(HttpStatus.OK.value());

			return new ResponseEntity(s, HttpStatus.OK);
		}
		
		throw new IdNotFoundException();
	}


	public String activeUser(String token) {
		User User=dao.activeUser(token);
		if(User!=null) {
			User.setToken(null);
			User.setStatus("Active");
			dao.updateUser(User);
			return "Your account is activated";
		}
		return "Invalid Link";
	}
	public ResponseEntity<ResponseStructure<Optional<List<Product>>>> findCartByUserId(int id){
		ResponseStructure<Optional<List<Product>>> s = new ResponseStructure<>();
		Optional<List<Product>> recProduct = dao.findByCartUserId(id);
		if(recProduct.isPresent()) {
			s.setBody(recProduct);
			s.setCode(HttpStatus.OK.value());
			s.setMessage("found cart");
			return new ResponseEntity<ResponseStructure<Optional<List<Product>>>>(s,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<Optional<List<Product>>>> findWishByUserId(int id){
		ResponseStructure<Optional<List<Product>>> s = new ResponseStructure<>();
		Optional<List<Product>> recProduct = dao.findWishByUserId(id);
		if(recProduct.isPresent()) {
			s.setBody(recProduct);
			s.setCode(HttpStatus.OK.value());
			s.setMessage("found wishlist");
			return new ResponseEntity<ResponseStructure<Optional<List<Product>>>>(s,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
}
