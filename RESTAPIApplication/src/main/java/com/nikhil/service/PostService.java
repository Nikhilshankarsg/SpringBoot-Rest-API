package com.nikhil.service;

import com.nikhil.Dto.PostDto;
import com.nikhil.Dto.PostResponseDto;

public interface PostService {
	
	public PostDto createPost(PostDto postDto);
	
	public PostResponseDto getAllPosts(int pageNo, int pageSize, String sortby, String sortDir);
	
	public PostDto getPostById(long id);

	public PostDto updatePostById(PostDto postDto, long id);
	
	public void deletePostById(long id);
}
