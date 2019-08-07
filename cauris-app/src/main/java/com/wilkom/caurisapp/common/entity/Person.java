package com.wilkom.caurisapp.common.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import com.wilkom.caurisapp.core.BaseEntity;


/**
 * Class Personne
 */
@MappedSuperclass
public abstract class Person extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum PersonType {
		MORALE, PHYSIQUE
	}

	public enum IDCardType {
		ID_CARD, PASSPORT, TRADER_CARD
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;
	@Column(nullable = false, name = "last_name")
	@NotNull (message="Saisir le nom svp!")
	protected String lastName;
	@Column(name = "first_name")
	@NotNull (message="Saisir le prenom svp!")
	protected String firstName;
	@Enumerated
	protected gender gender;
	@Embedded
	protected Adresse adresse;
	@Embedded
	protected Nationality nationality;
	@Embedded
	protected IDCard idCard;
	@Enumerated
	protected PersonType personType;
	@Embedded
	protected BirthID birthID;

	public Person() {
		super();
		this.personType = PersonType.PHYSIQUE;
		this.adresse = new Adresse();
		this.birthID = new BirthID();
		this.nationality = new Nationality();
		this.idCard = new IDCard();
	}

	public Person(String lastName, String firstName, gender gender, BirthID birthID, Adresse adresse) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.birthID = birthID;
		this.adresse = adresse;
		this.personType = PersonType.PHYSIQUE;
		this.nationality = new Nationality();
		this.idCard = new IDCard();
	}

	public Person(BirthID birthID, Adresse adresse) {
		super();
		this.adresse = adresse;
		this.birthID = birthID;
		this.nationality = new Nationality();
		this.idCard = new IDCard();
		this.personType = PersonType.PHYSIQUE;
	}

	public Person(Adresse adresse, Nationality nationality, IDCard idCard, BirthID birthID) {
		super();
		this.adresse = adresse;
		this.nationality = nationality;
		this.idCard = idCard;
		this.birthID = birthID;
		this.idCard = new IDCard();
		this.personType = PersonType.PHYSIQUE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public gender getGender() {
		return gender;
	}

	public void setGender(gender gender) {
		this.gender = gender;
	}

//	public Date getBirthDay() {
//		return birthDay;
//	}
//
//	public void setBirthDay(Date birthDay) {
//		this.birthDay = birthDay;
//	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
//		result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
//		if (birthDay == null) {
//			if (other.birthDay != null)
//				return false;
//		} else if (!birthDay.equals(other.birthDay))
//			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String fn = this.isPersonMorale() ? "" : " " + firstName;
		return lastName + fn;
	}

	public String getFullName() {
		String fn = this.isPersonMorale() ? "" : " " + firstName;
		return lastName + fn;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public IDCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public BirthID getBirthID() {
		return birthID;
	}

	public void setBirthID(BirthID birthID) {
		this.birthID = birthID;
	}

	/**
	 * Definit un simple boolean pour savoir si personne physique ou morale
	 * 
	 * @return TRUE si personne morale, FALSE si physique
	 */
	public boolean isPersonMorale() {
		System.out.println(personType);
		return this.personType.equals(PersonType.MORALE);
	}

}
