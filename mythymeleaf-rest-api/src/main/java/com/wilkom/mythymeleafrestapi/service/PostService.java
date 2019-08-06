package com.wilkom.mythymeleafrestapi.service;

import java.util.Collection;

import com.wilkom.mythymeleafrestapi.entity.Post;


/**
 * Interface fournissant les services de l'entité Post
 * 
 * @author Koffi
 *
 */
public interface PostService {

	/**
	 * Permet de lister tous les posts
	 * 
	 * @return Collection des objets Post
	 */
	Collection<Post> getAllPosts();

	/**
	 * Permet de récupérer un post par son identifiant
	 * 
	 * @param id
	 * @return un objet Post
	 */
	Post getPostById(Long id);
	
	/**
	 * Permet de récupérer un post par son title
	 * Important pour vérifier l'unicité du titre au cours des créations
	 * 
	 * @param id
	 * @return un objet Post
	 */
	Post getPostByTitle(String title);
	/**
	 * Persistence/mise à jour d'un post
	 * 
	 * @param post
	 * @return un objet Post après persistence
	 */
	Post saveOrUpdatePost(Post post);

	/**
	 * Permet de supprimer un post dont l'identifiant est id
	 * 
	 * @param id
	 */
	void deletePost(Long id);

	/**
	 * Vérifie si le post d'identifiant id existe
	 * @param id
	 * @return true si objet trouvé, false sinon
	 */
	boolean existsById(Long id);
}
