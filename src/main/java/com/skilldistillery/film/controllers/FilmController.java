package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.database.FilmDAO;
import com.skilldistillery.film.entities.Actor;
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
		return "result"; 
	}

	@RequestMapping(path = "NewActor.do", method = RequestMethod.POST)
	public String createActor(Actor actor, @RequestParam("filmTitle") String filmTitle, Model model) {
		Actor newActor = fd.createActor(actor, filmTitle);
		model.addAttribute("actor", newActor);
		model.addAttribute("actorSize", (fd.findFilmsByActorId(newActor.getId())).size());
		model.addAttribute("badActor", "Actor couldn't be added");
		return "result"; 
	}
	
	
	@RequestMapping(path = "UpdateFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film, Model model, RedirectAttributes redir, int filmId, @RequestParam("filmId") String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("filmEdit", fd.updateFilm(film));
		mv.addObject("film", fd.findFilmById(film.getFilmId()));
		model.addAttribute("editMessage", "Film was successfully updated.");
		model.addAttribute("editFail", "There was a problem updating the film.");
		mv.setViewName("result");
		return mv; 
	}
	
	@RequestMapping(path = "EditFilm.do", method = RequestMethod.GET)
	public ModelAndView editFilm(@RequestParam("filmId") String filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateFilmForm");
		Film film = fd.findFilmById(Integer.parseInt(filmId));
		mv.addObject("film", film);
		return mv; 
	}

	@RequestMapping(path = "Inventory.do", method = RequestMethod.GET)
	public ModelAndView inventory(@RequestParam("filmId") String filmId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("inventory", fd.findInventoryByFilmId(Integer.parseInt(filmId)));
		mv.setViewName("result");
		return mv; 
	}
	
	@RequestMapping(path = "ConfirmDelete.do", method = RequestMethod.GET)
	public ModelAndView confirmDelete(@RequestParam("filmId") int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("../deleteFilmForm");
		Film film = fd.findFilmById(filmId);
		mv.addObject("film", film);
		return mv; 
	}
	
	@RequestMapping(path = "DeleteFilm.do", method = RequestMethod.POST)
	public String deleteFilm(Film film, Model model) {
		model.addAttribute("filmDelete", fd.deleteFilm(film));
		model.addAttribute("deleteMessage", "Film was successfully deleted.");
		model.addAttribute("deleteFail", "There was a problem deleting the film.");
		return "result";
	}

	@RequestMapping(path = "GetFilm.do", method = RequestMethod.GET, params = "filmId")
	public String getFilmById(@RequestParam("filmId") int filmId, Model model) {
		model.addAttribute("film", fd.findFilmById(filmId));
		model.addAttribute("idMessage", "No film found");
		return "result"; 
	}

	@RequestMapping(path = "GetFilm.do", method = RequestMethod.GET, params = "keyword")
	public String getStateByAbbr(String keyword, Model model) {
		model.addAttribute("filmKeyword", fd.findFilmByKeyword(keyword));
		model.addAttribute("kwMessage", "There are no films that match your search");
		return "result";
	}
}