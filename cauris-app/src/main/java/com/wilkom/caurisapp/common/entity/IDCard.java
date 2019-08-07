package com.wilkom.caurisapp.common.entity;

/**
 * To change this template, choose Tools | Templates
*/
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.wilkom.caurisapp.common.entity.Person.IDCardType;

/**
 *
 * @author Wilson <wilkom2009@yahoo.fr>
 */
@Embeddable
public class IDCard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static IDCard defaultIDCard = new IDCard(IDCardType.ID_CARD, "AAAAA", new Date()); 
	
	@Enumerated
	private IDCardType type;
	@Column
	@NotNull (message="Saisir le numero de carte svp!")
	private String number;
	@Column(name = "expiry_day")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiryDay;

	public IDCard() {
	}

	
	public IDCard(IDCardType type, String number, Date expiryDay) {
		super();
		this.type = type;
		this.number = number;
		this.expiryDay = expiryDay;
	}


	public IDCardType getType() {
		return type;
	}

	public void setType(IDCardType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExpiryDay() {
		return expiryDay;
	}

	public void setExpiryDay(Date expiryDay) {
		this.expiryDay = expiryDay;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expiryDay, number, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDCard other = (IDCard) obj;
		return Objects.equals(expiryDay, other.expiryDay) && Objects.equals(number, other.number) && type == other.type;
	}

	@Override
	public String toString() {
		return type + " N° : " + number + ", Expiration  : " + new SimpleDateFormat("dd/MM/yyyy").format(expiryDay);
	}

}
