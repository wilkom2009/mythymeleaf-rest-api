package com.wilkom.caurisapp.portfolio.entity;

import java.util.Date;
import javax.persistence.*;

import com.wilkom.caurisapp.common.entity.Adresse;
import com.wilkom.caurisapp.common.entity.BirthID;
import com.wilkom.caurisapp.common.entity.IDCard;
import com.wilkom.caurisapp.common.entity.Nationality;
import com.wilkom.caurisapp.common.entity.Person;
import com.wilkom.caurisapp.common.entity.gender;

@Entity
@Table(name = "resource_persons")
public class ResourcePerson extends Person {
	
	public enum ResourceTypeEnum {
		AYANT_DROIT, RESPONSABLE
	}

	@Enumerated
	private ResourceTypeEnum resourceType;

	public ResourcePerson(String lastName, String firstName, gender gender,
			BirthID birthID, Adresse adresse, ResourceTypeEnum type) {
		super(lastName, firstName, gender, birthID, adresse);
		this.resourceType = type;
	}

	public ResourcePerson() {
		super();
	}

	public ResourcePerson(BirthID birthID, Adresse adresse) {
		super(birthID, adresse);
	}

	
	
	public ResourcePerson(Adresse adresse, Nationality nationality, IDCard idCard, BirthID birthID) {
		super(adresse, nationality, idCard, birthID);
	}

	public ResourceTypeEnum getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceTypeEnum resourceType) {
		this.resourceType = resourceType;
	}

	@Override
	public String toString() {
		return "ResourcePerson [resourceType=" + resourceType + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", gender=" + gender + ", adresse=" + adresse + ", nationality=" + nationality + ", idCard=" + idCard
				+ ", personType=" + personType + ", birthID=" + birthID + "]";
	}
}
