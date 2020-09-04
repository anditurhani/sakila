package it.objectmethod.sakila.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {

	@GeneratedValue
	@Id
	private Integer category_id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"), inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
	private List<Film> films;

	public Integer getCategoryId() {
		return category_id;
	}

	public void setCategoryId(Integer categoryId) {
		this.category_id = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
