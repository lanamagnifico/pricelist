package com.shestakova.pricelist.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class ItemCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="title")
	private String title;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH})
	@JoinTable(
			name = "items_categories_rel",
			joinColumns = @JoinColumn(name="category_id"),
			inverseJoinColumns = @JoinColumn(name="item_id"))
	private Set<Item> items;

	public ItemCategory() {

	}

	public ItemCategory(String title) {
		this.title = title;
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

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		if(items==null) {
			items = new HashSet<Item>();
		}
		items.add(item);	
	}

	@Override
	public String toString() {
		return "ItemCategory [id=" + id + ", title=" + title + ", items=" + Arrays.toString(items.toArray()) + "]";
	}

}
