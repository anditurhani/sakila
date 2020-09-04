package it.objectmethod.sakila.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.sakila.entity.Actor;
import it.objectmethod.sakila.entity.Category;
import it.objectmethod.sakila.entity.Film;
import it.objectmethod.sakila.repository.ActorRepository;
import it.objectmethod.sakila.repository.CategoryRepository;
import it.objectmethod.sakila.repository.FilmRepository;

@RestController
public class FilmController {

	@Autowired
	private ActorRepository actorRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private FilmRepository filmRepo;

	@GetMapping("/films")
	public List<Film> findFilms(@RequestParam(name = "searchStr", required = false) String searchStr,
			@RequestParam(name = "categoryName", required = false) String categoryName) {
		List<Film> filmList = new ArrayList<Film>();
		List<Film> filmsTmp = null;
		List<Category> categoryList = null;
		List<Actor> actorList = null;
		if (categoryName != null) {
			categoryList = categoryRepo.findByNameLike("%" + categoryName + "%");
		}
		if (searchStr != null) {
			String[] nameTmp = searchStr.split(" ");
			switch (nameTmp.length) {
			case 1: {
				String nameStr = "%" + nameTmp[0] + "%";
				actorList = actorRepo.findByFirstNameLike(nameStr);
				actorList.addAll(actorRepo.findByLastNameLike(nameStr));
			}
				break;
			case 2: {
				String firstName = "%" + nameTmp[0] + "%";
				String secondName = "%" + nameTmp[1] + "%";
				actorList = actorRepo.findByFirstNameLikeAndLastNameLike(firstName, secondName);
				actorList.addAll(actorRepo.findByFirstNameLikeAndLastNameLike(secondName, firstName));
			}
				break;
			}
		}
		if (!(searchStr == null || categoryName == null)) {
			for (Actor actor : actorList) {
				for (Category category : categoryList) {
					filmsTmp = filmRepo.findByActorsAndCategories(actor, category);
					filmList.addAll(filmsTmp);
				}
			}

		} else if (searchStr != null) {
			for (Actor actor : actorList) {
				filmsTmp = actor.getFilms();
				filmList.addAll(filmsTmp);
			}
		} else if (categoryName != null) {
			for (Category category : categoryList) {
				filmsTmp = category.getFilms();
				filmList.addAll(filmsTmp);
			}
		}
		return filmList;
	}
}
