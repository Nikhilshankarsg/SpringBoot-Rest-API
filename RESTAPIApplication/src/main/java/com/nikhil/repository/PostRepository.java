package com.nikhil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikhil.model.PostModel;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long>{

}
