package com.shestakova.pricelist.entity;

import java.util.HashSet;
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
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy="item",
			cascade= {CascadeType.ALL})
	private Set<ActualPrice> actualPrices;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH})
	@JoinTable(
			name = "items_categories_rel",
			joinColumns = @JoinColumn(name="item_id"),
			inverseJoinColumns = @JoinColumn(name="category_id"))
	private Set<ItemCategory> categories;

	public Item() {

	}

	public Item(String name) {
		super();
		this.title = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public Set<ActualPrice> getPricelist() {
		return actualPrices;
	}

	public void setPricelist(Set<ActualPrice> pricelist) {
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
		return "Item [id=" + id + ", name=" + title + "]";
	}
}
