package org.jsp.api.service;

import java.util.Optional;

import org.jsp.api.dao.AddressDao;
import org.jsp.api.dao.UserDao;
import org.jsp.api.dto.Address;
import org.jsp.api.dto.User;
import org.jsp.api.dto.Address;
import org.jsp.api.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
@Autowired
private AddressDao dao;
@Autowired
private UserDao udao;
public ResponseEntity<ResponseStructure<Address>> saveAddress(Address a ,int uid) {
	ResponseStructure<Address> structure = new ResponseStructure<>();
	Optional<User> recUser = udao.findUserById(uid);
	if (!recUser.isEmpty()) {
		User u = recUser.get();
		u.getAddresses().add(a);
		a.setUser(u);
		dao.saveAddress(a);
		structure.setBody(a);
		structure.setMessage("Address added succesfully");
		structure.setCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
	}
	throw new IdNotFoundException();
}

}
