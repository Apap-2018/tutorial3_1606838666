package com.apap.tutorial3.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CarModel {
	private String id;
	private String brand;
	private String type;
	private Long price;
	private Integer amount;
	
	public CarModel(String id, String brand, String type, Long price, Integer amount) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.brand = brand;
		this.type = type;
		this.price = price;
		this.amount = amount;
	}
	
	public CarModel() {
		// TODO Auto-generated constructor stub
		this.id = "";
		this.brand = "";
		this.type = "";
		this.price = null;
		this.amount = 0;
	}
	
	public String getId() {
		return id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getType() {
		return type;
	}
	
	public Long getPrice() {
		return price;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
