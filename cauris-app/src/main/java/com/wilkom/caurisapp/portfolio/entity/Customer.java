package com.wilkom.caurisapp.portfolio.entity;


import javax.persistence.*;

import com.wilkom.caurisapp.common.entity.Adresse;
import com.wilkom.caurisapp.common.entity.BirthID;
import com.wilkom.caurisapp.common.entity.IDCard;
import com.wilkom.caurisapp.common.entity.Nationality;
import com.wilkom.caurisapp.common.entity.Person;

@Entity
@Table(name = "customers")
public class Customer extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "customer_code")
	private String customerCode;
	@Embedded
	private PaymentMode mode;
	@ManyToOne
	private ResourcePerson ayantDroit;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "customer")
	private CustomerAccount account;

	public Customer() {
		super();
		this.mode = new PaymentMode();
	}

	public Customer(Adresse adresse) {
		super(new BirthID(), adresse);
		this.mode = new PaymentMode();
	}

	public Customer(Adresse adresse, Nationality nationality, IDCard idCard, BirthID birthID) {
		super(adresse, nationality, idCard, birthID);
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public ResourcePerson getAyantDroit() {
		return ayantDroit;
	}

	public void setAyantDroit(ResourcePerson ayantDroit) {
		this.ayantDroit = ayantDroit;
	}

	public CustomerAccount getAccount() {
		return account;
	}

	public void setAccount(CustomerAccount account) {
		this.account = account;
	}

	@Override
	public String toString() {
		String fn = this.isPersonMorale() ? "" : " " + firstName;
		return "[ " + customerCode + " ]   " + lastName + fn;
	}

}
