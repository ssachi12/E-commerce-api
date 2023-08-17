package org.jsp.api.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Product;
import org.jsp.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;

	public Product saveProduct( Product product) {
		return repository.save(product);
	}

	public Product updateProduct( Product product) {
		return repository.save(product);
	}

	public Optional<Product> findProduct( int id) {
		return repository.findById(id);
	}

	public List<Product> findAllProducts() {
		return repository.findAll();
	}

	public void deleteProduct(int id) {
		repository.deleteById(id);
	}

	public List<Product> fetchAllProductsByMerchantId(int mid) {
		
		return repository.findProductByMerchantId(mid);
	}
	
	public Optional<List<Product>> findByCategory(String category){
		return repository.findByCategory(category);
	}
}
