package com.erice.Belt.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Idea {
	@Id
	@GeneratedValue
	private long id;
	private String text;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	        name = "liker_liked_idea", 
	        joinColumns = @JoinColumn(name = "liked_idea_id" ), 
	        inverseJoinColumns = @JoinColumn(name = "liker_id")
	)
	private List<User> likers;
	
	private int Likes;
	
	public Idea() {
		this.Likes=0;
		
		
	}
	public Idea(String text, User author) {
		this.text=text;
		this.author=author;
		this.Likes=0;
		
	}
	
	
	public int getLikes() {
		return Likes;
	}
	public void setLikes(int likes) {
		this.Likes = Likes;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public List<User> getLikers() {
		return likers;
	}
	public void setLikers(List<User> likers) {
		this.likers = likers;
	}
	public long getId() {
		return id;
	}
	
	

	

}
