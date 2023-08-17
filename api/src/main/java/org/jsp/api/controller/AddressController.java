package org.jsp.api.controller;

import org.jsp.api.dao.AddressDao;
import org.jsp.api.dto.Address;
import org.jsp.api.service.AddressService;
import org.jsp.api.service.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000/")
public class AddressController {
	@Autowired
	private AddressService service;
	@PostMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address a,@PathVariable int uid){
	return	service.saveAddress(a, uid);
	}

}
