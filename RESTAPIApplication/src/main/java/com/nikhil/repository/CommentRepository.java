package com.nikhil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikhil.model.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long>{
	
	  List<CommentModel> findByPostModel_Id(Long postId);

	

}
