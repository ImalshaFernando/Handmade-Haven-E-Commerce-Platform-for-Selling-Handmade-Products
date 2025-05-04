package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	 @Id @GeneratedValue
	    private Long id;

	    @ManyToOne
	    private Cart cart;

	    @ManyToOne
	    private Product product;

	    private Integer quantity;

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
}
