package com.wilkom.caurisapp.portfolio.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("CUSTOMER_ACCOUNT")
public class CustomerAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Customer customer;

	public CustomerAccount() {
		super();
	}

	public CustomerAccount(Date dateCreation, Customer customer) {
		super(dateCreation);
		this.customer = customer;
	}

	public CustomerAccount(String accountNumber, Date dateCreation, Customer customer) {
		super(accountNumber, dateCreation);
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerAccount other = (CustomerAccount) obj;
		return Objects.equals(customer, other.customer);
	}

	@Override
	public String toString() {
		return accountNumber;
	}

}
