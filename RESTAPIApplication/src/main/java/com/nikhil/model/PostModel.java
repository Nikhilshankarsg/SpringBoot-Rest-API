package com.nikhil.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class PostModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany(mappedBy ="postModel", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CommentModel> commentModel = new HashSet<>();

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getDescription() {
		return description;
	}

	public Set<CommentModel> getCommentModel() {
		return commentModel;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCommentModel(Set<CommentModel> commentModel) {
		this.commentModel = commentModel;
	}
}
