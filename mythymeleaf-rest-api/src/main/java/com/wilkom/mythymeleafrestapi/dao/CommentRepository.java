package com.wilkom.mythymeleafrestapi.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wilkom.mythymeleafrestapi.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("Select c from Comment c where c.post.id = ?1")
	Collection<Comment> getCommentsByPostId(Long postId);
}
