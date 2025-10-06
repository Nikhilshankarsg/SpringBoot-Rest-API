package com.nikhil.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nikhil.Dto.CommentDto;
import com.nikhil.exception.BlogAPIException;
import com.nikhil.exception.ResourceNotFoundException;
import com.nikhil.model.CommentModel;
import com.nikhil.model.PostModel;
import com.nikhil.repository.CommentRepository;
import com.nikhil.repository.PostRepository;
import com.nikhil.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		//Dto to Entity
		CommentModel commentModel = mapToEntity(commentDto);
		
		//retrive post entity by id
		PostModel postModel = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		//Set PostModel to CommentModel
		commentModel.setPostModel(postModel);
		
		//Set CommentModel to DB
		CommentModel newCommentModel = commentRepository.save(commentModel);
		 
		return mapToDto(newCommentModel);
	}
	

	@Override
	public List<CommentDto> getCommentsPostById(long postId) {
		 
	   List<CommentModel> commentModel = commentRepository.findByPostModel_Id(postId);
	   
	   return commentModel.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	   
	}
	
	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		
		//retrive post entity by id
		PostModel postModel = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		CommentModel commentModel = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!commentModel.getPostModel().getId().equals(postModel.getId())){
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Doesnot Belongs to Post");
		}
		return mapToDto(commentModel);
	}
	
	@Override
	public CommentDto updateCommentById(long postId, long commentId, CommentDto commentRequest) {

		PostModel postModel = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		CommentModel commentModel = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!commentModel.getPostModel().getId().equals(postModel.getId())){
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Doesnot Belongs to Post");
		}
		
		commentModel.setUsername(commentRequest.getUsername());
		commentModel.setEmail(commentRequest.getEmail());
		commentModel.setBody(commentRequest.getBody());
		
			CommentModel updateCommentModel = commentRepository.save(commentModel);
			
		return mapToDto(updateCommentModel);
	}

	
	//Dto to Entity
	private CommentModel mapToEntity(CommentDto commentDto) {
		CommentModel commentModel = new CommentModel();
		//commentModel.setId(commentDto.getId());
		commentModel.setUsername(commentDto.getUsername());
		commentModel.setEmail(commentDto.getEmail());
		commentModel.setBody(commentDto.getBody());
		return commentModel;
	}
	
	//Entity to Dto
	private CommentDto mapToDto(CommentModel commentModel) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(commentModel.getId());
		commentDto.setUsername(commentModel.getUsername());
		commentDto.setEmail(commentModel.getEmail());
		commentDto.setBody(commentModel.getBody());
		return commentDto;
	}


	@Override
	public void deleteComment(long postId, long commentId) {
		
PostModel postModel = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		CommentModel commentModel = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!commentModel.getPostModel().getId().equals(postModel.getId())){
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Doesnot Belongs to Post");
		}
		
		commentRepository.delete(commentModel);
		
		
	}
}
