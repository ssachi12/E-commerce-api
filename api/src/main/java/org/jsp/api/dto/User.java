package org.jsp.api.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
@Column(unique = true)
private String email;
@Column(unique = true)
private long phone;
private String password;
private String token;
private String status;
@OneToMany
@JoinTable(name = "user_cart", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="product_Id"))
private List<Product> cart;
@OneToMany
@JoinTable(name = "user_wishlist", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="product_Id"))
private List<Product> wishlist;
@OneToMany(mappedBy = "user")
private List<Address> addresses;
}
