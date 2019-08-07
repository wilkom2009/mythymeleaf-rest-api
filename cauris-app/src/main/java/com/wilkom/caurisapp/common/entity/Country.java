package com.wilkom.caurisapp.common.entity;

import javax.persistence.*;

import com.wilkom.caurisapp.core.BaseEntity;


/**
 * 
 * @author Wilson
 */
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

	@Id
	private int code;
	@Column(name = "nom_fr", nullable = false)
	private String nom;
	@Column(name = "nom_en", nullable = false)
	private String nomEng;
	@Column(length = 2)
	private String alpha2;
	@Column(length = 3)
	private String alpha3;
	@Column
	private String nationalite;
	@Column
	private int id;

	public Country() {
	}

	public Country(int code, String nom, String nomEng, String alpha2, String alpha3, String nationalite) {
		super();
		this.code = code;
		this.nom = nom;
		this.nomEng = nomEng;
		this.alpha2 = alpha2;
		this.alpha3 = alpha3;
		this.nationalite = nationalite;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 71 * hash + this.code;
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
		final Country other = (Country) obj;
		if (this.code != other.code) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nom;
	}

	public String getNomEng() {
		return nomEng;
	}

	public void setNomEng(String nomEng) {
		this.nomEng = nomEng;
	}

	public String getAlpha2() {
		return alpha2;
	}

	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}

	public String getAlpha3() {
		return alpha3;
	}

	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
