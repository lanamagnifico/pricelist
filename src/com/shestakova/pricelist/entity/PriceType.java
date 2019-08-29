package com.shestakova.pricelist.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="price_types")
public class PriceType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY,
			mappedBy="priceType", 
			cascade = CascadeType.ALL)
	private Set<PriceReg> regList;

	@OneToMany(fetch = FetchType.LAZY,
			mappedBy="priceType", 
			cascade = CascadeType.ALL)
	private Set<ActualPrice> priceList;
	
	public PriceType() {

	}

	public PriceType(String name) {
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

	public Set<PriceReg> getRegList() {
		return regList;
	}

	public void setRegList(Set<PriceReg> regList) {
		this.regList = regList;
	}

	public Set<ActualPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(Set<ActualPrice> priceList) {
		this.priceList = priceList;
	}

	@Override
	public String toString() {
		return "PriceType [id=" + id + ", name=" + name + "]";
	}
}
