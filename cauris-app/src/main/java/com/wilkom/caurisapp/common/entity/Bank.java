package com.wilkom.caurisapp.common.entity;

import java.util.Objects;
import javax.persistence.*;

import com.wilkom.caurisapp.core.BaseEntity;


/**
 *
 * @author Wilson
 */
@Entity
@Table(name = "banks")
public class Bank extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer code;

    @Column(nullable = false)
    private String name;
    
    @Column
    private String swift;

    @Embedded
    private Adresse adresse;

    public Bank() {
    	super();
    	this.adresse = new Adresse(new City());
    }

    public Bank(String nom, String swift, Adresse adresse) {
		super();
		this.name = nom;
		this.swift = swift;
		this.adresse = adresse;
	}

	public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.code;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.adresse);
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
        final Bank other = (Bank) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
