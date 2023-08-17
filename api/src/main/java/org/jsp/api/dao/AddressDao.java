package org.jsp.api.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Address;
import org.jsp.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
@Autowired
private AddressRepository repository;
public Address saveAddress(Address address){
	return repository.save(address);
}
public Address updateAddress(Address address){
	return repository.save(address);
}
public Optional<Address> findAddressByID(int id) {
	return repository.findById(id);
}
public List<Address> findAddress(int id){
	return repository.findAddressByUserId(id);
}
public void deleteAddress(int id) {
	 repository.deleteById(id);
}

}
