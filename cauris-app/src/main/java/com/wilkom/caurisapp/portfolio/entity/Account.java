package com.wilkom.caurisapp.portfolio.entity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.wilkom.caurisapp.common.entity.Bank;
import com.wilkom.caurisapp.core.BaseEntity;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class Account extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_number")
	protected String accountNumber;// n° automatique

	@Column(name = "date_creation")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date dateCreation;
	@Column
	protected boolean open;
	@Column
	protected Double amount;

	public Account() {
		super();
		this.amount = Double.valueOf(0);
		this.open = Boolean.TRUE;
	}

	public Account(Date dateCreation) {
		super();
		this.dateCreation = dateCreation;
		this.open = Boolean.TRUE;
		this.amount = Double.valueOf(0);
	}

	protected Account(String accountNumber, Date dateCreation) {
		super();
		this.accountNumber = accountNumber;
		this.dateCreation = dateCreation;
		this.open = Boolean.TRUE;
		this.amount = Double.valueOf(0);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFormattedDateCreation() {
		return dateCreation == null ? "***" : new SimpleDateFormat("dd/MM/yyyy").format(dateCreation);
	}

	public String getFormattedAmount() {
		return NumberFormat.getInstance(Locale.FRANCE).format(amount == null ? 0d : amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, amount, dateCreation, open);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(amount, other.amount)
				&& Objects.equals(dateCreation, other.dateCreation) && open == other.open;
	}

	@Override
	public String toString() {
		return accountNumber;
	}

}
