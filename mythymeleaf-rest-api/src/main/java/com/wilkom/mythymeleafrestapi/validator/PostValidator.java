package com.wilkom.mythymeleafrestapi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wilkom.mythymeleafrestapi.entity.Post;
import com.wilkom.mythymeleafrestapi.service.PostService;


/**
 * Gestion des Validators pour le model Post
 * @author Koffi
 *
 */
@Component
public class PostValidator implements Validator {

	@Autowired
	private PostService postService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Post.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Post post = (Post) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.post.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty.post.body");

		if (post.getId() == null) {
			// vérification de l'unicité du titre
			if (postService.getPostByTitle(post.getTitle()) != null) {
				errors.rejectValue("title", "Valid.post.title");
			}
		}
	}

}