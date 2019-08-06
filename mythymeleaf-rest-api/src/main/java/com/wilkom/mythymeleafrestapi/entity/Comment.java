package com.wilkom.mythymeleafrestapi.entity;

import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Gestion des commentaires
 * Classe Entité pour mapper les objets Comment dans la base de donneés et inversement
 * @author Koffi
 *
 */
@Entity
@Table(name = "COMMENTS")
@XmlRootElement(name = "comment")//Permet également de générer des documents XML à partir d'une instance de la classe
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "COMMENT_BODY", nullable = false)
	private String body;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "POST_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Post post;

	public Comment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Comment(String body, Post post) {
		super();
		this.body = body;
		this.post = post;
	}


	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	@XmlElement
	public void setBody(String body) {
		this.body = body;
	}

	public Post getPost() {
		return post;
	}

	@XmlElement
	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, id, post);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(body, other.body) && Objects.equals(id, other.id) && Objects.equals(post, other.post);
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", body=" + body + ", post=" + post + "]";
	}

}