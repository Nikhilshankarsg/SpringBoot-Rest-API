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
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.Dto.CommentDto;
import com.nikhil.service.CommentService;


@RestController
@RequestMapping("/v1/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, 
													@RequestBody CommentDto commentDto){
		
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
		
		return commentService.getCommentsPostById(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentsById(@PathVariable(value = "postId") long postId, 
													  @PathVariable(value = "id") long commentId){
		
		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentsById(@PathVariable(value = "postId") long postId, 
													  @PathVariable(value = "id") long commentId,
													  @RequestBody CommentDto commentDto){
		
		CommentDto updatedComment = commentService.updateCommentById(postId, commentId, commentDto);
		
		return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId, 
											    @PathVariable(value = "id") long commentId){
		
		commentService.deleteComment(postId, commentId);
		
		return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
	}
	
	
	
	

}
