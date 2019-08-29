package com.shestakova.pricelist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="price")
public class ActualPrice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne(cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="price_type_id")
	private PriceType priceType;
	
	@Column(name="price_value")
	private int value;
	
	public ActualPrice() {
	}

	public ActualPrice(Item item, PriceType pricetype, int value) {
		super();
		this.item = item;
		this.priceType = pricetype;
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

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType pricetype) {
		this.priceType = pricetype;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", item=" + item + ", pricetype=" + priceType + ", value=" + value + "]";
	}
	
}
