package com.alibou.ecommerce.category;

import java.util.List;

import com.alibou.ecommerce.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@SequenceGenerator(name = "id_seq", allocationSize = 1, sequenceName = "category_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_seq")
	private Integer id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<Product> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
