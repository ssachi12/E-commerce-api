package org.jsp.api.service;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dao.MerchantDao;
import org.jsp.api.dao.ProductDao;
import org.jsp.api.dao.UserDao;
import org.jsp.api.dto.Merchant;
import org.jsp.api.dto.Product;
import org.jsp.api.dto.User;
import org.jsp.api.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao pdao;
	@Autowired
	private MerchantDao mdao;
	@Autowired
	private UserDao udao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product p, int mid) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = mdao.findMerchantById(mid);
		if (!recMerchant.isEmpty()) {
			Merchant m = recMerchant.get();
			m.getProduct().add(p);
			p.setMerchant(m);
			pdao.saveProduct(p);
			structure.setBody(p);
			structure.setMessage("Product added succesfully");
			structure.setCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product p, int mid, int pid) {
		ResponseStructure<Product> s = new ResponseStructure<>();
		Optional<Merchant> recMerchant = mdao.findMerchantById(mid);
		if (!recMerchant.isEmpty()) {
			Merchant m = recMerchant.get();
			m.getProduct().add(p);
			p.setMerchant(m);
			p.setId(pid);
			pdao.updateProduct(p);
			s.setBody(p);
			s.setMessage("Product added succesfully");
			s.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(s, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Product>>> fetchAllProductsByMerchantId(int mid) {
		ResponseStructure<List<Product>> s = new ResponseStructure<>();
		s.setBody(pdao.fetchAllProductsByMerchantId(mid));
		s.setMessage("product found");
		s.setCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(s, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Optional<Product>>> findProduct(int id) {
		ResponseStructure<Optional<Product>> structure = new ResponseStructure<>();
		Optional<Product> p = pdao.findProduct(id);
		if (p.isPresent()) {
			structure.setBody(p);
			structure.setMessage("Product found");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Optional<Product>>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = pdao.findAllProducts();
		if (products.size() > 0) {
			structure.setBody(products);
			structure.setMessage("Displayed all products");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		structure.setBody(null);
		structure.setMessage("no prodcuts");
		structure.setCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> recProduct = pdao.findProduct(id);
		if (recProduct.isPresent()) {
			pdao.deleteProduct(id);
			structure.setBody("Product deleted");
			structure.setMessage("Product found");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> addToWishList(int pid, int uid) {
		Optional<Product> recProduct = pdao.findProduct(pid);
		Optional<User> recUser = udao.findUserById(uid);
		ResponseStructure<String> s = new ResponseStructure<>();
		if (recProduct.isPresent() && recUser.isPresent()) {
			recUser.get().getWishlist().add(recProduct.get());
			udao.updateUser(recUser.get());
			s.setBody("product added to the wishlist");
			s.setMessage("user and product found");
			s.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(s, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> addToCart(int pid, int uid) {
		Optional<Product> recProduct = pdao.findProduct(pid);
		Optional<User> recUser = udao.findUserById(uid);
		ResponseStructure<String> s = new ResponseStructure<>();
		if (recProduct.isPresent() && recUser.isPresent()) {
			recUser.get().getCart().add(recProduct.get());
			udao.updateUser(recUser.get());
			s.setBody("product added to the cart");
			s.setMessage("user and product found");
			s.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(s, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> rateProduct(int pid, int uid, double rating) {
		Optional<Product> recProduct = pdao.findProduct(pid);
		Optional<User> recUser = udao.findUserById(uid);
		ResponseStructure<Product> s=new ResponseStructure<>();
		if(recUser.isPresent() &&recProduct.isPresent()) {
			Product p=recProduct.get();
			int n=p.getNo_of_users();
			double r=p.getRating()*n++;
			rating= (r+rating)/n;
			p.setNo_of_users(n);
			p.setRating(rating);
			pdao.updateProduct(p);
			s.setBody(p);
			s.setMessage("product rated");
			s.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(s, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<Optional<List<Product>>>> findBycategory(String category) {
		ResponseStructure<Optional<List<Product>>> structure = new ResponseStructure<>();
		Optional<List<Product>> products = pdao.findByCategory(category);
		if (products.isPresent()) {
			structure.setBody(products);
			structure.setMessage("Displayed all products");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Optional<List<Product>>>>(structure, HttpStatus.OK);
		}
		structure.setBody(null);
		structure.setMessage("no prodcuts");
		structure.setCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Optional<List<Product>>>>(structure, HttpStatus.NOT_FOUND);
	}
}
