package com.alibou.ecommerce.orderline;

import com.alibou.ecommerce.order.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_line")
public class OrderLine {

	@Id
	@SequenceGenerator(name = "id_seq" ,allocationSize = 1 , sequenceName = "customer_order_seq" )
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "id_seq")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "quantity")
	private double quantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

}
