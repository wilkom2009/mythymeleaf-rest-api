package com.wilkom.caurisapp.common.entity;

/**
 * To change this template, choose Tools | Templates
*/
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Wilson <wilkom2009@yahoo.fr>
 */
@Embeddable
public class Adresse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String street;
	private String box;
	private String quarter;
	@ManyToOne
	private City city;
	@NotNull (message="Saisir le numero de telephone svp!")
	private String phone;
	private String email;
	private String web;

	public Adresse() {
//		this.street = "***";
//		this.box = "***";
//		this.phone = "***";
//		this.email = "***";
//		this.quarter = "***";
	}

	public Adresse(String street, String box, String quarter, City city, String phone, String email, String web) {
		super();
		this.street = street;
		this.box = box;
		this.quarter = quarter;
		this.city = city;
		this.phone = phone;
		this.email = email;
		this.web = web;
	}

	public Adresse(City city) {
		super();
		this.city = city;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email == null ? "***" : email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web == null ? "***" : web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * 
	 * @return l'adresse sans le téléphone
	 */
	public String getWithoutTel() {
		return "Rue:" + street + ", Bp:" + box + ", Pays:" + city.getCountry() + ", Ville:" + city;
	}

	/**
	 * 
	 * @return l'adresse sans le téléphone, ni le pays
	 */
	public String getWithoutTelPays() {
		return "Rue:" + street + ", Bp:" + box + ", Ville:" + city;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 61 * hash + (this.street != null ? this.street.hashCode() : 0);
		hash = 61 * hash + (this.box != null ? this.box.hashCode() : 0);
		hash = 61 * hash + (this.city != null ? this.city.hashCode() : 0);
		hash = 61 * hash + (this.phone != null ? this.phone.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Adresse other = (Adresse) obj;
		if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
			return false;
		}
		if ((this.box == null) ? (other.box != null) : !this.box.equals(other.box)) {
			return false;
		}
		if (this.city != other.city && (this.city == null || !this.city.equals(other.city))) {
			return false;
		}
		if ((this.phone == null) ? (other.phone != null) : !this.phone.equals(other.phone)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String v = city == null ? "" : " - " + city.toString();
		String s = street == null ? "" : street;
		String q = quarter == null ? "" : ", " + quarter;
		String b = box == null ? "" : ", " + box;
		String p = phone == null ? "" : ", Tel.: " + phone;
		String e = email == null ? "" : ", Email : " + email;
		String w = web == null ? "" : ", Site web : " + web;

		return s + q + b + v + p + e + w;
	}

}
