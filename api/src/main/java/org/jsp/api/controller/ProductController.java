package org.jsp.api.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.api.dto.Product;
import org.jsp.api.service.ProductService;
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

@RestController
@CrossOrigin("http://localhost:3000/")
public class ProductController {
	@Autowired
	private ProductService service;
@PostMapping("/product/{id}")
public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,@PathVariable int id ){
	return service.saveProduct(product, id);
}
@PutMapping("/product/{id}/{pid}")
public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product,@PathVariable int id,@PathVariable int pid ){
	return service.updateProduct(product, id ,pid);
}
@GetMapping("/product/{mid}")
public ResponseEntity<ResponseStructure<List<Product>>> fetchAllProductsByMerchantId(@PathVariable int mid){
	return service.fetchAllProductsByMerchantId(mid);
}
@DeleteMapping("/product/{pid}")
public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int pid){
	return service.deleteProduct(pid);
}
@GetMapping("/product/fetch/{id}")
public ResponseEntity<ResponseStructure<Optional<Product>>> fetchProductById(@PathVariable int id){
	return service.findProduct(id);
}
@PostMapping("/product/wish/{pid}/{uid}")
public ResponseEntity<ResponseStructure<String>> addToWishlist(@PathVariable int pid,@PathVariable int uid){
	return service.addToWishList(pid, uid);
}
@PostMapping("/product/cart/{pid}/{uid}")
public ResponseEntity<ResponseStructure<String>> addToCart(@PathVariable int pid,@PathVariable int uid){
	return service.addToCart(pid, uid);
}
@PutMapping("/product/rate/{pid}/{uid}")
public ResponseEntity<ResponseStructure<Product>> rateProduct(@PathVariable int pid,@PathVariable int uid,@RequestParam int rate){
	return service.rateProduct(pid, uid, rate);
}
@GetMapping("/product")
public ResponseEntity<ResponseStructure<List<Product>>> fetchAllproduct(){
	return service.findAllProducts();
}
@GetMapping("/product/category/{category}")
ResponseEntity<ResponseStructure<Optional<List<Product>>>> findByCategory(@PathVariable String category){
	return service.findBycategory(category);
}
}