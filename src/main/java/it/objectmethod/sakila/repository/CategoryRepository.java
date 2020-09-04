package it.objectmethod.sakila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.sakila.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public List<Category> findByNameLike(String categoryName);

}
