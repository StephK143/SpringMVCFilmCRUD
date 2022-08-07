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
	
	
	@RequestMapping(path = "UpdateFilm.do", method = RequestMethod.GET)
	public ModelAndView updateFilm(Film film, Model model, RedirectAttributes redir, int filmId, @RequestParam("filmId") String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/result.jsp");
		System.out.println("***************************** film: " + film);
		System.out.println("****************************  filmId: " + filmId);
//		model.addAttribute("filmEdit", fd.updateFilm(film));
//		model.addAttribute("editMessage", "Film was successfully edited.");
//		model.addAttribute("editFail", "There was a problem updating the film.");
		return mv; // placeholder page until we write .jsp
	}
	@RequestMapping(path = "EditFilm.do", method = RequestMethod.GET)
	public ModelAndView editFilm(@RequestParam("filmId") String filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateFilmForm");
		Film film = fd.findFilmById(Integer.parseInt(filmId));
		mv.addObject("film", film);
		
//		model.addAttribute("filmDelete", fd.deleteFilm(film));
//		model.addAttribute("deleteMessage", "Film was successfully deleted.");
//		model.addAttribute("deleteFail", "There was a problem deleting the film.");
		return mv; // placeholder page until we write .jsp   -----redirect to another form using model object
	}
	
	@RequestMapping(path = "DeleteFilm.do", method = RequestMethod.POST)
	public String deleteFilm(Film film, Model model) {
		model.addAttribute("filmDelete", fd.deleteFilm(film));
		model.addAttribute("deleteMessage", "Film was successfully deleted.");
		model.addAttribute("deleteFail", "There was a problem deleting the film.");
		return "result"; // placeholder page until we write .jsp   -----redirect to another form using model object
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
		model.addAttribute("kwPrompt", "To edit/delete a film in this list, search by the film id.");  //Can we automate filmID data transfer
		model.addAttribute("kwMessage", "There are no films that match your search");
		return "result"; // placeholder page until we write .jsp
	}
	
	@RequestMapping(path = "InputFilm.do", method = RequestMethod.GET, params = "filmId")
	public String inputFilmToEditor(@RequestParam("filmId") int filmId, Model model) {
		model.addAttribute("film", fd.findFilmById(filmId));
		model.addAttribute("idMessage", "No film found");
		return "updateFilmForm"; // placeholder page until we write .jsp
	}
	
}