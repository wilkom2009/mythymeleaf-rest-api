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

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Wilson <wilkom2009@yahoo.fr>
 */
@Embeddable
public class BirthID implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BirthID defaultBirthID = new BirthID(new Date(), null);
	
	@Column(name = "birth_day")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDay;
	@ManyToOne
	private City birthCity;

	public BirthID() {
	}

	public BirthID(Date birthDay, City ville) {
		super();
		this.birthDay = birthDay;
		this.birthCity = ville;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public City getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(City birthCity) {
		this.birthCity = birthCity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDay, birthCity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BirthID other = (BirthID) obj;
		return Objects.equals(birthDay, other.birthDay) && Objects.equals(birthCity, other.birthCity);
	}

	@Override
	public String toString() {
		String c = birthCity == null ? "" : ", à " + birthCity + " - " + birthCity.getCountry().getNom();
		return new SimpleDateFormat("dd/MM/yyyy").format(birthDay) + c;
	}

}
