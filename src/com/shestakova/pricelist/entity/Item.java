package com.shestakova.pricelist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@OneToMany(mappedBy="item",cascade= {CascadeType.ALL})
	private List<ActualPrice> actualPrices;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.ALL})
	@JoinTable(
			 name = "item_price_registration",
			 joinColumns=@JoinColumn(name="item_id"),
			 inverseJoinColumns=@JoinColumn(name="price_registration_id"))
	private List<PriceReg> priceRegistrationList;
	
	public Item() {

	}

	public Item(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ActualPrice> getPricelist() {
		return actualPrices;
	}

	public void setPricelist(List<ActualPrice> pricelist) {
		this.actualPrices = pricelist;
	}

	public List<PriceReg> getPriceRegistrationList() {
		return priceRegistrationList;
	}

	public void setPriceRegistrationList(List<PriceReg> priceRegistrationList) {
		this.priceRegistrationList = priceRegistrationList;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}

}
