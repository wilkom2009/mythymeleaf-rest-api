package com.wilkom.mythymeleafrestapi.service.impl;

import java.util.Collection;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilkom.mythymeleafrestapi.dao.PostRepository;
import com.wilkom.mythymeleafrestapi.entity.Post;
import com.wilkom.mythymeleafrestapi.exception.BusinessResourceException;
import com.wilkom.mythymeleafrestapi.service.PostService;


@Service(value = "postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Collection<Post> getAllPosts() {
		return IteratorUtils.toList(postRepository.findAll().iterator());
	}

	@Override
	@Transactional(readOnly = false)
	public Post saveOrUpdatePost(Post post) {
		try {
			return postRepository.save(post);
		} catch (Exception ex) {
			throw new BusinessResourceException("Erreur de création/mise à jour", 
					"Erreur de création/mise à jour du post : " + post.getTitle(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePost(Long id) {
		try {
			postRepository.deleteById(id);
		} catch (Exception ex) {
			throw new BusinessResourceException("Erreur de suppression", "Erreur de suppression du post N°: " + id,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Post getPostById(Long id) {
		return postRepository.getOne(id);
	}

	@Override
	public boolean existsById(Long id) {
		return postRepository.existsById(id);
	}

	@Override
	public Post getPostByTitle(String title) {
		return postRepository.getPostByTitle(title);
	}

}
