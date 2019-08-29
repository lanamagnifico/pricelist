package com.shestakova.pricelist.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="price_reg")
public class PriceReg {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date_reg")
	private Date startDate;
	
	@ManyToOne(cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="price_type_id")
	private PriceType priceType;	
	
	@OneToMany(fetch = FetchType.EAGER, 
			   mappedBy="priceReg",
			   cascade=CascadeType.ALL)
	private List<PriceRegDetail> details;

	public PriceReg() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<PriceRegDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PriceRegDetail> details) {
		this.details = details;
	}

	public void addItemAndPrice(PriceRegDetail tmpPriceRegDetail) {
		if (details==null) {
			details = new ArrayList<PriceRegDetail>();
		}	
		details.add(tmpPriceRegDetail);
		tmpPriceRegDetail.setPriceReg(this);
	}
	
	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	@Override
	public String toString() {
		return "PriceReg [id=" + id + ", startDate=" + startDate + ", priceType=" + priceType + ", details=" + Arrays.toString(details.toArray())
				+ "]";
	}
	
}
