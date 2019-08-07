package com.wilkom.caurisapp.common.entity;

/**
 * To change this template, choose Tools | Templates
*/
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author Wilson <wilkom2009@yahoo.fr>
 */
@Embeddable
public class Nationality implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Country country;

	public Nationality() {
	}

	protected Nationality(Country country) {
		super();
		this.country = country;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nationality other = (Nationality) obj;
		return Objects.equals(country, other.country);
	}

	@Override
	public String toString() {
		return country.getNationalite();
	}

}
