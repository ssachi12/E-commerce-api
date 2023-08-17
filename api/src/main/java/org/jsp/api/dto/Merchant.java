package org.jsp.api.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
@Column(unique = true)
private String email;
@Column(unique = true)
private long phone;
private String password;
@Column(unique = true)
private String gst;
private String token;
private String status;
@OneToMany(mappedBy = "merchant")
private List<Product> product;
}
