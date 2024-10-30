package com.example.webts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserBody {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private Integer weight;
	
	@Column(nullable = false)
	private Integer height;
	
	@Column(nullable = false)
	private Integer age;
	
	private String active;
	
	private double BMR;
	
	private double total;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;
	
}
