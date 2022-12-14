package com.skilldistillery.film.database;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.InventoryItem;

public interface FilmDAO {
	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public List<Film> findFilmsByActorId(int actorId);

	public List<Film> findFilmByKeyword(String keyword);

	public List<InventoryItem> findInventoryByFilmId(int filmId);

	public Film createFilm(Film film);

	public boolean updateFilm(Film film);

	public boolean deleteFilm(Film film);

	Actor createActor(Actor actor, String filmTitle);
}
