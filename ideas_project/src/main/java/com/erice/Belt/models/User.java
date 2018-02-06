package com.erice.Belt.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;


@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Size(min=2, max=20, message="first name must be between 2 and 20 characters")
	private String first_name;
	@Size(min=2, max=20, message="last name must be between 2 and 20 characters")
	private String last_name;
	@Size(min=2, max=20, message="alias must be between 2 and 20 characters")
	private String alias;
	
	private String email;
	private String pwordhash;
	@OneToMany(mappedBy="author", fetch=FetchType.LAZY)
	private List<Idea> Ideas;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	        name = "liker_liked_idea", 
	        joinColumns = @JoinColumn(name = "liker_id" ), 
	        inverseJoinColumns = @JoinColumn(name = "liked_idea_id")
	)
	private List<Idea> liked_ideas;
	
	
	public User() {
		
	}
	
	
	public User(String first_name, String last_name,String email, String pwordhash, String alias){
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.pwordhash=pwordhash;
		this.alias=alias;

	}
	






	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public List<Idea> getIdeas() {
		return Ideas;
	}


	public void setIdeas(List<Idea> ideas) {
		this.Ideas = ideas;
	}


	public List<Idea> getLiked_ideas() {
		return liked_ideas;
	}


	public void setLiked_ideas(List<Idea> liked_ideas) {
		this.liked_ideas = liked_ideas;
	}


	public Date getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}



	public Date getCreatedat() {
		return createdat;
	}




	private Date createdat;
	private Date updatedat;
	@Size(min=2, max=20, message="username must be between 2 and 20 characters")
	public String username;
	
	
	
	
	


    @PrePersist
    protected void onCreate(){
        this.createdat = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedat = new Date();
    }
    
    


	public String getFirst_name() {
		return first_name;
	}




	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}




	public String getLast_name() {
		return last_name;
	}




	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPwordhash() {
		return pwordhash;
	}




	public void setPwordhash(String pwordhash) {
		this.pwordhash = pwordhash;
	}




	public long getId() {
		return id;
	}
	

}
