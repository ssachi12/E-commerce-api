package org.jsp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name, brand, category;
	@Column(length = 1000)
	private String description;
	private double cost, rating;
	@Column(length = 1000)
	private String image;
	private int no_of_users;
	private String model;
	private double discount;
	private int stocks;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Merchant merchant;
}
