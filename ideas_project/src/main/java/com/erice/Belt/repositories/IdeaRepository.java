package com.erice.Belt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erice.Belt.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {



	

}
