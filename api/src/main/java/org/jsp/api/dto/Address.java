package org.jsp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String area,landmark,city,state,country,addressType;
private int houseNo,pincode;
@ManyToOne
@JoinColumn(name="user_id")
@JsonIgnore
private User user;
}
