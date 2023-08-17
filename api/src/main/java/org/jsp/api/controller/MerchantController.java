package org.jsp.api.controller;

import org.jsp.api.dto.Merchant;
import org.jsp.api.service.MerchantService;
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
public class MerchantController {
	@Autowired
	private MerchantService service;

	@PostMapping("/merchant")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant,
			HttpServletRequest request) {
		return service.saveMerchant(merchant, request);
	}

	@GetMapping("/merchant/active")
	public String activeMerchant(@RequestParam String token) {
		return service.activeMerchant(token);
	}

	@GetMapping("/merchant/reset")
	public String forgotPassword(@RequestParam String email, HttpServletRequest request) {
		return service.sendPasswordLink(email, request);
	}

	@PutMapping("/merchant")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return service.updateMerchant(merchant);
	}

	@GetMapping("/merchant/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findMerchant(@PathVariable int id) {
		return service.findMerchantById(id);
	}

	@PostMapping("/merchant/login-ph")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchantByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return service.verifyMerchantByPhone(phone, password);
	}

	@PostMapping("/merchant/login-em")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchantByEmail(@RequestParam String email,
			@RequestParam String password) {
		return service.verifyMerchantByEmail(email, password);
	}

	@DeleteMapping("/merchant/{id}")
	public ResponseEntity<ResponseStructure<String>> Deletemerchant(@PathVariable int id) {
		return service.deleteMerchant(id);
	}
}
