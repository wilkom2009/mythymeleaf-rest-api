package com.wilkom.mythymeleafrestapi.controller;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wilkom.mythymeleafrestapi.entity.Post;
import com.wilkom.mythymeleafrestapi.exception.BusinessResourceException;
import com.wilkom.mythymeleafrestapi.service.CommentService;
import com.wilkom.mythymeleafrestapi.service.PostService;
import com.wilkom.mythymeleafrestapi.validator.PostValidator;

/**
 * Gestion des Posts
 * Classe controller qui fournit les services REST et les CRUD sur les pages JSP
 * @author Koffi K. 
 *
 */
@Controller
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;

	@Autowired
	private PostValidator postValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(postValidator);
	}

	// Page JSP liste des posts
	@GetMapping("/posts")
	public String readAll(Model model) {
		logger.info("Liste des posts ici ...");
		model.addAttribute("posts", postService.getAllPosts());

		return "posts/list";
	}

	// Page jsp détails d'un post
	@GetMapping("/posts/{postId}")
	public String read(@PathVariable("postId") long id, Model model) {
		Post post = postService.getPostById(id);
		if (post == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Post d'ID : " + id + " n'existe pas");
			return "redirect:/posts";
		}
		model.addAttribute("post", post);
		model.addAttribute("commentsList", commentService.getCommentsByPostId(post.getId()));

		return "posts/read";
	}

	// Supprimer post
	@PostMapping("/posts/{postId}/delete")
	public String delete(@PathVariable("postId") long id, final RedirectAttributes redirectAttributes) {
		postService.deletePost(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Post supprimé !");

		return "redirect:/posts";
	}

	// Enregistrer ou mettre à jour un post
	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute("create") @Validated Post myPost, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "posts/create";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if (myPost.getId() == null) {
				redirectAttributes.addFlashAttribute("msg", "Post créé avec succès!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Modification réussie!");
			}

			postService.saveOrUpdatePost(myPost);

			return "redirect:/posts";
		}
	}

	// Afficher create form
	@RequestMapping(value = "/posts/create", method = RequestMethod.GET)
	public String showForm(Model model) {
		Post post = new Post();
		model.addAttribute("mypost", post);

		return "posts/create";
	}

	// Afficher update form
	@GetMapping("/posts/{postId}/update")
	public String showUpdateForm(@PathVariable("postId") long id, Model model) {
		model.addAttribute("post", postService.getPostById(id));

		return "posts/update";

	}

	
	/**
	 * Methode service REST pour afficher les posts
	 * @return
	 */
	@GetMapping("/post/posts")
	public ResponseEntity<Collection<Post>> getAllPosts() {
		return new ResponseEntity<Collection<Post>>(postService.getAllPosts(), HttpStatus.FOUND);
	}

	
	/**
	 * Methode service REST pour afficher un post
	 * @param postId
	 * @return
	 */
	@GetMapping("/test/{id}")
	public ResponseEntity<String> getId(@PathVariable("id") String id) {
		logger.info("Id : " + id);
		return new ResponseEntity<String>("Voici mr "+id, HttpStatus.FOUND);
	}

	
	/**
	 * Methode service REST pour afficher un post
	 * @param postId
	 * @return
	 */
	@GetMapping("/post/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
		logger.info("Post trouvé : " + postService.getPostById(postId));
		return new ResponseEntity<Post>(postService.getPostById(postId), HttpStatus.FOUND);
	}

	/**
	 * Methode service REST pour ajouter les posts
	 * @param post
	 * @return
	 */
	@PostMapping("/post/posts")
	@Transactional // Gérer les transactions
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
		return new ResponseEntity<Post>(postService.saveOrUpdatePost(post), HttpStatus.CREATED);
	}

	/**
	 * Methode service REST pour modifier un post
	 * @param postId
	 * @param postRequest
	 * @return
	 */
	@PutMapping("/post/{postId}")
	@Transactional // Gérer les transactions
	public ResponseEntity<Post> updatePost(@PathVariable(value = "postId") Long postId,
			@Valid @RequestBody Post postRequest) {
		if (!postService.existsById(postId)) {
			throw new BusinessResourceException("Post inexistant", "Le post d'ID : " + postId + " n'existe pas!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Post post = postService.getPostById(postId);
		post.setBody(postRequest.getBody());
		return new ResponseEntity<Post>(postService.saveOrUpdatePost(post), HttpStatus.CREATED);
	}

	/**
	 * Methode service REST pour supprimer un post
	 * @param postId
	 * @return
	 */
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable(value = "postId") Long postId) {
		if (!postService.existsById(postId)) {
			throw new BusinessResourceException("Post inexistant", "Le post d'ID : " + postId + " n'existe pas!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		postService.deletePost(postId);
		return new ResponseEntity<Void>(HttpStatus.GONE);
	}

}
