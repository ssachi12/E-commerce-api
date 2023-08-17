package org.jsp.api.service;

import java.util.HashMap;
import java.util.Optional;
import org.jsp.api.dao.MerchantDao;
import org.jsp.api.dto.EmailConfigaration;
import org.jsp.api.dto.Merchant;
import org.jsp.api.exception.CrNotFoundException;
import org.jsp.api.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;
	@Autowired
	private EmailConfigaration config;
	@Autowired
	private ShoppingCartMailService mailservice;
	@Autowired
	private GenerateLinkService linkService;
	@Autowired
	private GetResetPasswordLink resetService;
//save merchant
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant,HttpServletRequest request) {
		config.setSubject("Registration successfull");
		dao.saveMerchant(merchant);
		HashMap<String, String> map=new HashMap<>();
		map.put("email", merchant.getEmail());
		map.put("name", merchant.getName());
		config.setText("Hello Mr "+merchant.getName() 
		+" You have successfully initiated the registration for our e-commerce store"+
				"please click on the link "+linkService.getVerificationLink(request, merchant));
		config.setUser(map);
		mailservice.sendWelcomeMail(config);
		ResponseStructure<Merchant> s = new ResponseStructure<>();
		s.setMessage("Merchant succcessfully saved");
		s.setBody(merchant);
		s.setCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(s, HttpStatus.CREATED);
	}
	public String sendPasswordLink(String email,HttpServletRequest request) {
		Merchant merchant=dao.findMerchantByEmail(email);
		if(merchant!=null) {
			HashMap<String, String> map=new HashMap<>();
			map.put("email", merchant.getEmail());
			map.put("name", merchant.getName());
			config.setText(
			" You can change the password by below link"+
					"please click on the link "+resetService.resetPasswordLink(request, merchant));
			config.setUser(map);
			mailservice.sendWelcomeMail(config);
			return "reset password link sent to email";
		}
		return "you have not registered";
	}

//	update merchant
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		ResponseStructure<Merchant> s = new ResponseStructure<>();
		s.setMessage("Merchant succcessfully updated");
		s.setBody(dao.updateMerchant(merchant));
		s.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(s, HttpStatus.ACCEPTED);
	}

//	find merchant by id
	public ResponseEntity<ResponseStructure<Merchant>> findMerchantById(int id) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = dao.findMerchantById(id);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant found");
			structure.setBody(recMerchant.get());
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}

		throw new IdNotFoundException();

	}

//	verify merchant by phone number
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchantByPhone(long phone, String password) {
		ResponseStructure<Merchant> s = new ResponseStructure<>();
		Optional<Merchant> recMerchant = dao.verifyByPhone(phone, password);
		if (recMerchant.isPresent()) {
			s.setBody(recMerchant.get());
			s.setMessage("Merchant verified successfully");
			s.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(s, HttpStatus.OK);
		}

		throw new CrNotFoundException();

	}
//	merchant login by email
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchantByEmail(String email, String password) {
		ResponseStructure<Merchant> s = new ResponseStructure<>();
		Optional<Merchant> recMerchant = dao.verifyByEmail(email, password);
		if (recMerchant.isPresent()) {
			s.setBody(recMerchant.get());
			s.setMessage("Merchant verified successfully");
			s.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(s, HttpStatus.OK);
		}

		throw new CrNotFoundException();

	}
//	delete Merchant
	public ResponseEntity<ResponseStructure<String>> deleteMerchant(int id) {
		ResponseStructure<String> s = new ResponseStructure<>();
		Optional<Merchant> recMerchant = dao.findMerchantById(id);
		if (recMerchant.isPresent()) {
			dao.deleteMerchant(id);
			s.setMessage("Merchant found");
			s.setBody("Merchant deleted suceesfully");
			s.setCode(HttpStatus.OK.value());

			return new ResponseEntity(s, HttpStatus.OK);
		}
		s.setMessage("Merchant not found");
		s.setBody("Merchant not deleted");
		s.setCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity(s, HttpStatus.NOT_FOUND);
	}


	public String activeMerchant(String token) {
		Merchant merchant=dao.activeMerchant(token);
		if(merchant!=null) {
			merchant.setToken(null);
			merchant.setStatus("Active");
			dao.updateMerchant(merchant);
			return "Your account is activated";
		}
		return "Invalid Link";
	}
	
}
