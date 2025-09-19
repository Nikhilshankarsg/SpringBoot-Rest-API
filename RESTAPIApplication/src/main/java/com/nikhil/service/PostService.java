package com.nikhil.service;

import java.util.List;

import com.nikhil.Dto.PostDto;

public interface PostService {
	
	public PostDto createPost(PostDto postDto);
	
	public List<PostDto> getAllPosts();
	
	public PostDto getPostById(long id);

	public PostDto updatePostById(PostDto postDto, long id);
	
	public void deletePostById(long id);
}
