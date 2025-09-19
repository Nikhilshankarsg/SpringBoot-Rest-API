package com.nikhil.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.Dto.PostDto;
import com.nikhil.exception.ResourceNotFoundException;
import com.nikhil.model.PostModel;
import com.nikhil.repository.PostRepository;
import com.nikhil.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public PostDto createPost(PostDto postDto) {
		
		//convert dto to entity
		PostModel postModel = mapToEntity(postDto);
		
		PostModel savePostModel = postRepository.save(postModel);
		
		//convert Entity to Dto
		PostDto postResponse = mapToDto(savePostModel);
		
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<PostModel> postModel = postRepository.findAll();
		
		return postModel.stream().map(postModels -> mapToDto(postModels)).collect(Collectors.toList());
	}
	
	@Override
	public PostDto getPostById(long id) {
		PostModel postModel = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDto(postModel);
	}
	
	@Override
	public PostDto updatePostById(PostDto postDto, long id) {
		PostModel postModel = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		postModel.setTitle(postDto.getTitle());
		postModel.setContent(postDto.getDescription());
		postModel.setDescription(postDto.getDescription());
		
		PostModel updatedPostModel = postRepository.save(postModel);
		
		return mapToDto(updatedPostModel);
	}
	
	@Override
	public void deletePostById(long id) {
		PostModel postModel = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(postModel);
	}
	
	//convert entity to Dto
	private PostDto mapToDto(PostModel postModel) {
		PostDto postDto = new PostDto();
		postDto.setId(postModel.getId());
		postDto.setTitle(postModel.getTitle());
		postDto.setContent(postModel.getContent());
		postDto.setDescription(postModel.getDescription());
		return postDto;
	}
	
	//convert Dto to Entity
	private PostModel mapToEntity(PostDto postDto) {
		PostModel postModel = new PostModel();
		postModel.setTitle(postDto.getTitle());
		postModel.setContent(postDto.getContent());
		postModel.setDescription(postDto.getDescription());
		return postModel;
	}
}
