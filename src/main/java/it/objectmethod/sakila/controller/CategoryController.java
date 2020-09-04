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

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ActorRepository actorRepo;

	@GetMapping("/all-categories")
	public List<Category> findAllCategories() {
		List<Category> categoryList = null;
		categoryList = categoryRepo.findAll();
		return categoryList;
	}

	@GetMapping("/categories")
	public List<Category> findCategories(@RequestParam(name = "searchStr") String searchStr) {
		List<Actor> actorList = null;
		List<Film> filmsTmp = null;
		List<Film> filmList = new ArrayList<Film>();
		List<Category> categoriesTmp = new ArrayList<Category>();
		List<Category> categoryList = new ArrayList<Category>();
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

		for (Actor actor : actorList) {
			filmsTmp = actor.getFilms();
			filmList.addAll(filmsTmp);
		}

		for (Film film : filmList) {
			categoriesTmp.addAll(film.getCategories());

		}
		for (Category category : categoriesTmp) {
			if (!categoryList.contains(category)) {
				categoryList.add(category);
			}
		}
		return categoryList;
	}

}
