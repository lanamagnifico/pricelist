package com.shestakova.pricelist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="price_type")
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
	private List<PriceReg> regList;

	@OneToMany(fetch = FetchType.LAZY,
			mappedBy="priceType", 
			cascade = CascadeType.ALL)
	private List<ActualPrice> priceList;
	
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

	public List<PriceReg> getRegList() {
		return regList;
	}

	public void setRegList(List<PriceReg> regList) {
		this.regList = regList;
	}

	public List<ActualPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<ActualPrice> priceList) {
		this.priceList = priceList;
	}

	@Override
	public String toString() {
		return "PriceType [id=" + id + ", name=" + name + "]";
	}
}
