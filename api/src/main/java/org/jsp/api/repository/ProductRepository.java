package org.jsp.api.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select p from Product p where p.merchant.id=?1")
	public List<Product> findProductByMerchantId(int merchant_id);

	@Query("select p from Product p where p.id=?1")
	public Optional<Product> findById(int id);
	
	@Query("select p from Product p where p.category=?1")
	public Optional<List<Product>> findByCategory(String category);
	
}
