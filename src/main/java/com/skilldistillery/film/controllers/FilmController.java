package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.film.database.FilmDAO;
import com.skilldistillery.film.database.FilmDAOImpl;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	// Still need to add mappings for other methods/functions

	@Autowired
	private FilmDAO fd;

	public FilmDAO getFilmdDAO() {
		return fd;
	}

	public void setFilmDAO(FilmDAO fd) {
		this.fd = fd;
	}

	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public String createFilm(Film film, Model model) {
		model.addAttribute("film", fd.createFilm(film));
		return "result"; // placeholder page until we write .jsp
	}
	
	@RequestMapping(path = "UpdateFilm.do", method = RequestMethod.POST)
	public String updateFilm(Film film, Model model) {
		model.addAttribute("filmEdit", fd.updateFilm(film));
		return "result"; // placeholder page until we write .jsp
	}

	@RequestMapping(path = "GetFilm.do", method = RequestMethod.GET, params = "filmId")
	public String getFilmById(@RequestParam("filmId") int filmId, Model model) {
		model.addAttribute("film", fd.findFilmById(filmId));
		model.addAttribute("idMessage", "No film found");
		return "result"; // placeholder page until we write .jsp
	}

	@RequestMapping(path = "GetFilm.do", method = RequestMethod.GET, params = "keyword")
	public String getStateByAbbr(String keyword, Model model) {
		model.addAttribute("filmKeyword", fd.findFilmByKeyword(keyword));
		model.addAttribute("kwMessage", "There are no films that match your search");
		return "result"; // placeholder page until we write .jsp
	}
}