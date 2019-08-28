package com.shestakova.pricelist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="price")
public class Price {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="item_id")
	private Item item;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="price_type_id")
	private PriceType pricetype;
	@Column(name="price_value")
	private int value;
	
	public Price() {
	}

	public Price(Item item, PriceType pricetype, int value) {
		super();
		this.item = item;
		this.pricetype = pricetype;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public PriceType getPricetype() {
		return pricetype;
	}

	public void setPricetype(PriceType pricetype) {
		this.pricetype = pricetype;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", item=" + item + ", pricetype=" + pricetype + ", value=" + value + "]";
	}
	
}
