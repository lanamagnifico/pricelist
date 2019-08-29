package com.shestakova.pricelist.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy="item",
			cascade= {CascadeType.ALL})
	private List<ActualPrice> actualPrices;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH})
	@JoinTable(
			name = "item_category_rel",
			joinColumns = @JoinColumn(name="item_id"),
			inverseJoinColumns = @JoinColumn(name="category_id"))
	private Set<ItemCategory> categories;

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

	public void addCategory(ItemCategory category) {
		if (categories == null) {
			categories = new HashSet<ItemCategory>();
		}
		categories.add(category);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
}
