package com.shestakova.pricelist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="price_reg_detail")
public class PriceRegDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="price_reg_id")
	private PriceReg priceReg;
	
	@OneToOne(cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="item_id")
	private Item item;
	
	@Column(name="new_value")
	private int newValue;
	
	public PriceRegDetail() {
		
	}

	public PriceRegDetail(Item item, int newValue) {
		this.item = item;
		this.newValue = newValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PriceReg getPriceReg() {
		return priceReg;
	}

	public void setPriceReg(PriceReg registrator) {
		this.priceReg = registrator;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getNewValue() {
		return newValue;
	}

	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}
	
}
