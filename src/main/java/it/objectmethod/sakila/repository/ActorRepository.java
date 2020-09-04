package it.objectmethod.sakila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.sakila.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	public List<Actor> findByFirstNameLike(String firstNameStr);

	public List<Actor> findByLastNameLike(String lastNameStr);

	public List<Actor> findByFirstNameLikeAndLastNameLike(String firstNameStr, String lastNameLike);

}
