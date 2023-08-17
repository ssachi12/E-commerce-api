package org.jsp.api.dao;

import java.util.Optional;

import org.jsp.api.dto.Merchant;
import org.jsp.api.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
@Autowired
private MerchantRepository repository;

public Merchant saveMerchant(Merchant merchant) {
	return repository.save(merchant);
}
public Merchant updateMerchant(Merchant merchant) {
	return repository.save(merchant);
}
public Optional<Merchant> findMerchantById(int id) {
	return repository.findById(id);
}
public void deleteMerchant(int id){
	 repository.deleteById(id);
}
public Optional<Merchant> verifyByPhone(long phone,String password){
	return repository.verifyByPhone(phone, password);
}
public Optional<Merchant> verifyByEmail(String email,String password){
	return repository.verifyByEmail(email, password);
}
public Merchant activeMerchant(String token) {
	return repository.findmerchantByToken(token);
}
public Merchant findMerchantByEmail(String email) {
	// TODO Auto-generated method stub
	return repository.findmerchantByEmail(email);
}
}
