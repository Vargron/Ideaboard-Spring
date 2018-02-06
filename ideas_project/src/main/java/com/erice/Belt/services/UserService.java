package com.erice.Belt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erice.Belt.models.User;
import com.erice.Belt.repositories.UserRepository;
@Service
public class UserService {
	private UserRepository userrepo;
	public UserService(UserRepository userrepo) {
		this.userrepo=userrepo;
	}
	public void adduser(User user) {
		userrepo.save(user);
	}
	public List<User> allusers(){
		return (List<User>) userrepo.findAll();
	}
	public User findbyemail(String email) {
		return userrepo.findByEmail(email);
		
		
	}
	public User getuser(Long id) {
		return userrepo.findOne(id);
	}
}
