package it.objectmethod.sakila.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.sakila.entity.Actor;
import it.objectmethod.sakila.entity.Film;
import it.objectmethod.sakila.repository.FilmRepository;

@RestController
public class ActorController {

	@Autowired
	private FilmRepository filmRepo;

	@GetMapping("/actors")
	public List<Actor> findActors(@RequestParam(name = "title") String title) {
		List<Actor> actorList = new ArrayList<Actor>();
		List<Actor> actorsTmp = null;
		List<Film> filmList = null;
		filmList = filmRepo.findByTitleLike("%" + title + "%");
		for (Film film : filmList) {
			actorsTmp = film.getActors();
			actorList.addAll(actorsTmp);
		}
		return actorList;

	}

}
