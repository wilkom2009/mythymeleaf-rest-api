package com.wilkom.mythymeleafrestapi.service;

import java.util.Collection;

import com.wilkom.mythymeleafrestapi.entity.Comment;


/**
 * Interface fournissant les services de l'entité Comment
 * 
 * @author Koffi
 *
 */
public interface CommentService {

		/**
	 * Renvoie les commentaires d'un post
	 * 
	 * @param postId
	 * @return Collection des commentaires dont l'identifiant du post est postId
	 */
	Collection<Comment> getCommentsByPostId(Long postId);

	/**
	 * Persistence d'un commentaire d'un poste d'identifiant postId
	 * 
	 * @param postId
	 * @param comment
	 * @return Commentaire inséré
	 */
	Comment saveOrUpdateComment(Long postId, Comment comment);

	/**
	 * Suppression des commentaires d'un post d'identifiant postId
	 * 
	 * @param postId
	 */
	void deleteComment(Long postId);
	
	/**
	 * Vérifie si le commentaire d'identifiant id existe
	 * @param id
	 * @return true si objet trouvé, false sinon
	 */
	boolean existsById(Long id);

	/**
	 * Récupère un commentaire via son identifiant
	 * @param id
	 * @return un objet Comment
	 */
	Comment getCommentById(Long id);
}
