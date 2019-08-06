package com.wilkom.mythymeleafrestapi.entity;

import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Gestion des Posts
 * Classe Entité pour mapper les objets Post dans la base de donneés et inversement
 * @author Koffi
 *
 */
@Entity
@Table(name = "POSTS")
@XmlRootElement(name = "post")//Permet également de générer des documents XML à partir d'une instance de la classe
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Post extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "POST_TITLE", unique = true, insertable = true, updatable = true, nullable = false)
	private String title;

	@Column(name = "POST_BODY", nullable = false)
	private String body;

	public Post() {
		super();
	}

	public Post(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public Post(Long id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	@XmlElement
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(body, other.body) && Objects.equals(id, other.id) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", body=" + body + "]";
	}

}