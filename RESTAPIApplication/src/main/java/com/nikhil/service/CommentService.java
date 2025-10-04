package com.nikhil.service;

import java.util.List;

import com.nikhil.Dto.CommentDto;

public interface CommentService {
	
	CommentDto createComment(long postId, CommentDto commentDto);
	
	List<CommentDto> getCommentsPostById(long postId);
	
	CommentDto getCommentById(long postId, long commentId);
	
	CommentDto updateCommentById(long postId, long commentId, CommentDto commentRequest);

}
