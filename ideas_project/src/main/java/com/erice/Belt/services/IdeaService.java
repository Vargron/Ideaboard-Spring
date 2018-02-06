package com.erice.Belt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erice.Belt.models.Idea;
import com.erice.Belt.models.User;
import com.erice.Belt.repositories.IdeaRepository;

@Service
public class IdeaService {
	private IdeaRepository idearepo;
	public IdeaService(IdeaRepository idearepo) {
		this.idearepo=idearepo;
		
	}
	
	public void addidea(Idea idea) {
		idearepo.save(idea);
	}
	public Idea getidea(Long id) {
		return idearepo.findOne(id);
	}
	public List<Idea> allideas(){
		return (List<Idea>)idearepo.findAll();
	}
//	public List<Idea> ideassorted(){
//		return (List<Idea>)idearepo.findAllOrderByLikesDesc();
//	}
	public Boolean likeidea(Idea idea, User user) {
		List<User>likers= idea.getLikers();
		for(int i=0; i<likers.size();i++) {
			if (likers.get(i).getId()==user.getId()) {
		
				return false;
			}
		}
		likers.add(user);
		idea.setLikers(likers);
		idea.setLikes(likers.size());
		idearepo.save(idea);
		return true;
	}
	public void deleteidea(Idea idea) {
		idearepo.delete(idea);
	}

}
