package com.wilkom.caurisapp.common.entity;

import java.util.Objects;

import javax.persistence.*;

import com.wilkom.caurisapp.core.BaseEntity;

/**
 * Class City
 */
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	private Country country;

	private boolean alone; // définit si la ville est liée à une autre donnée ou pas (pour suppression)

	protected City() {
		super();
		this.alone = true;
	}

	public City(String name, Country country) {
		super();
		this.name = name;
		this.country = country;
		this.alone = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public boolean isAlone() {
		return alone;
	}

	public void setAlone(boolean alone) {
		this.alone = alone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(country, other.country) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}

}
