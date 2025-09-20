package com.nikhil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.Dto.PostDto;
import com.nikhil.Dto.PostResponseDto;
import com.nikhil.constants.ApplicationConstant;
import com.nikhil.service.PostService;

@RestController
@RequestMapping("v1/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/createPost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllPost")
	//public List<PostDto> getAllPosts(
	public PostResponseDto getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = ApplicationConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = ApplicationConstant.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ApplicationConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			){
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	@GetMapping("/getPostById/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@PutMapping("updatePostById/{id}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){
		PostDto postResponse = postService.updatePostById(postDto, id);
		return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("deletePostById/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id){
		postService.deletePostById(id);
		return new ResponseEntity("Post Entity is Deleted Successfully", HttpStatus.CREATED);
	}
}
