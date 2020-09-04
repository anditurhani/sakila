package it.objectmethod.sakila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.sakila.entity.Actor;
import it.objectmethod.sakila.entity.Category;
import it.objectmethod.sakila.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

	public List<Film> findByActorsAndCategories(Actor actors, Category categories);

	public List<Film> findByTitleLike(String title);

}
