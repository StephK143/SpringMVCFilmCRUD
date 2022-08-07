package com.skilldistillery.film.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.InventoryItem;

public class FilmDAOImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static String user = "student";
	private static String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
	}

	public FilmDAOImpl() {
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film f JOIN film_category fc ON f.id = fc.film_id JOIN category c ON fc.category_id = c.id JOIN language l ON f.language_id = l.id WHERE f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film = new Film(filmResult.getInt("f.id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getInt("release_year"),
						filmResult.getInt("language_id"), filmResult.getString("l.name"),
						filmResult.getInt("rental_duration"), filmResult.getDouble("rental_rate"),
						filmResult.getInt("length"), filmResult.getDouble("replacement_cost"),
						filmResult.getString("rating"), filmResult.getString("special_features"),
						filmResult.getString("c.name"), findActorsByFilmId(filmId), findInventoryByFilmId(filmId));
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
						actorResult.getString("last_name"));
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmActors = stmt.executeQuery();

			while (filmActors.next()) {
				actors.add(new Actor(filmActors.getInt("id"), filmActors.getString("first_name"),
						filmActors.getString("last_name")));
			}
			filmActors.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		List<Film> films = new ArrayList<Film>();

		String sql = "SELECT * FROM film f JOIN film_category fc ON f.id = fc.film_id JOIN category c ON fc.category_id = c.id JOIN language l ON f.language_id = l.id WHERE title LIKE ? OR description LIKE ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);

			ResultSet filmRS = stmt.executeQuery();

			while (filmRS.next()) {
				films.add(new Film(filmRS.getInt("f.id"), filmRS.getString("title"), filmRS.getString("description"),
						filmRS.getInt("release_year"), filmRS.getInt("language_id"), filmRS.getString("l.name"),
						filmRS.getInt("rental_duration"), filmRS.getDouble("rental_rate"), filmRS.getInt("length"),
						filmRS.getDouble("replacement_cost"), filmRS.getString("rating"),
						filmRS.getString("special_features"), filmRS.getString("c.name"),
						findActorsByFilmId(filmRS.getInt("f.id")), findInventoryByFilmId(filmRS.getInt("f.id"))));
			}
			filmRS.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<InventoryItem> findInventoryByFilmId(int filmId) {
		List<InventoryItem> copies = new ArrayList<InventoryItem>();
		String sql = "SELECT * FROM film f JOIN inventory_item i ON f.id = i.film_id JOIN store s ON i.store_id = s.id JOIN address a ON s.address_id = a.id WHERE f.id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmCopies = stmt.executeQuery();

			while (filmCopies.next()) {
				copies.add(new InventoryItem(filmCopies.getString("f.title"), filmCopies.getInt("i.id"),
						filmCopies.getString("media_condition"), filmCopies.getString("address") + ", "
								+ filmCopies.getString("city") + ", " + filmCopies.getString("state_province")));
			}
			filmCopies.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return copies;
	}

	// CODE THAT DEE WAS WORKING WITH...

	@Override
	public Actor createActor(Actor actor) {
//		  Connection conn = null;
//		  try {
//		    conn = DriverManager.getConnection(url, user, pass);
//		    conn.setAutoCommit(false); // START TRANSACTION
//		    String sql = "INSERT INTO actor (first_name, last_name) "
//		                     + " VALUES (?,?)";
//		    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//		    stmt.setString(1, actor.getFirstName());
//		    stmt.setString(2, actor.getLastName());
//		    int updateCount = stmt.executeUpdate();
//		    if (updateCount == 1) {
//		      ResultSet keys = stmt.getGeneratedKeys();
//		      if (keys.next()) {
//		        int newActorId = keys.getInt(1);
//		        actor.setId(newActorId);
//		        if (actor.getFilms() != null && actor.getFilms().size() > 0) {
//		          sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//		          stmt = conn.prepareStatement(sql);
//		          for (Film film : actor.getFilms()) {
//		            stmt.setInt(1, film.getId());
//		            stmt.setInt(2, newActorId);
//		            updateCount = stmt.executeUpdate();
//		          }
//		        }
//		      }
//		    } else {
//		      actor = null;
//		    }
//		    conn.commit(); // COMMIT TRANSACTION
//		  } catch (SQLException sqle) {
//		    sqle.printStackTrace();
//		    if (conn != null) {
//		      try { conn.rollback(); }
//		      catch (SQLException sqle2) {
//		        System.err.println("Error trying to rollback");
//		      }
//		    }
//		    throw new RuntimeException("Error inserting actor " + actor);
//		  }
		return actor;
	}

	@Override
	public boolean updateActor(Actor actor) {
//		  Connection conn = null;
//		  try {
//		    conn = DriverManager.getConnection(url, user, pass);
//		    conn.setAutoCommit(false); // START TRANSACTION
//		    String sql = "UPDATE actor SET first_name=?, last_name=? "
//		               + " WHERE id=?";
//		    PreparedStatement stmt = conn.prepareStatement(sql);
//		    stmt.setString(1, actor.getFirstName());
//		    stmt.setString(2, actor.getLastName());
//		    stmt.setInt(3, actor.getId());
//		    int updateCount = stmt.executeUpdate();
//		    if (updateCount == 1) {
//		      // Replace actor's film list
//		      sql = "DELETE FROM film_actor WHERE actor_id = ?";
//		      stmt = conn.prepareStatement(sql);
//		      stmt.setInt(1, actor.getId());
//		      updateCount = stmt.executeUpdate();
//		      sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//		      stmt = conn.prepareStatement(sql);
//		      for (Film film : actor.getFilms()) {
//		        stmt.setInt(1, film.getId());
//		        stmt.setInt(2, actor.getId());
//		        updateCount = stmt.executeUpdate();
//		      }
//		      conn.commit();           // COMMIT TRANSACTION
//		    }
//		  } catch (SQLException sqle) {
//		    sqle.printStackTrace();
//		    if (conn != null) {
//		      try { conn.rollback(); } // ROLLBACK TRANSACTION ON ERROR
//		      catch (SQLException sqle2) {
//		        System.err.println("Error trying to rollback");
//		      }
//		    }
//		    return false;
//		  }
		return true;
	}

	@Override
	public boolean deleteActor(Actor actor) {
//		  Connection conn = null;
//		  try {
//		    conn = DriverManager.getConnection(url, user, pass);
//		    conn.setAutoCommit(false); // START TRANSACTION
//		    String sql = "DELETE FROM film_actor WHERE actor_id = ?";
//		    PreparedStatement stmt = conn.prepareStatement(sql);
//		    stmt.setInt(1, actor.getId());
//		    int updateCount = stmt.executeUpdate();
//		    sql = "DELETE FROM actor WHERE id = ?";
//		    stmt = conn.prepareStatement(sql);
//		    stmt.setInt(1, actor.getId());
//		    updateCount = stmt.executeUpdate();
//		    conn.commit();             // COMMIT TRANSACTION
//		  }
//		  catch (SQLException sqle) {
//		    sqle.printStackTrace();
//		    if (conn != null) {
//		      try { conn.rollback(); }
//		      catch (SQLException sqle2) {
//		        System.err.println("Error trying to rollback");
//		      }
//		    }
//		    return false;
//		  }
		return true;
	}

	@Override
	public Film createFilm(Film film) {
		int newFilmId = 0;
		int catId = 0;
		ResultSet categoryId;
		 Connection conn = null;
		  try {
		    conn = DriverManager.getConnection(URL, user, pass);
		    conn.setAutoCommit(false); // START TRANSACTION
		    String sql = "INSERT INTO film  (title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, language_id) "
		                     + " VALUES (?,?,?,?,?,?,?,?, 1)";
		    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    stmt.setString(1, film.getTitle());
		    stmt.setString(2, film.getDescription());
		    stmt.setInt(3, film.getReleaseYear());
		    stmt.setInt(4, film.getDuration());
		    stmt.setDouble(5, film.getRentalRate());
		    stmt.setInt(6, film.getLength());
		    stmt.setDouble(7, film.getReplaceCost());
		    stmt.setString(8, film.getRating());
		    int updateCount = stmt.executeUpdate();
		    if (updateCount == 1) {
		      ResultSet keys = stmt.getGeneratedKeys();
		      if (keys.next()) {
		        newFilmId = keys.getInt(1);
		        film.setFilmId(newFilmId);
//		        if (actor.getFilms() != null && actor.getFilms().size() > 0) {
//		          sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//		          stmt = conn.prepareStatement(sql);
//		          for (Film film : actor.getFilms()) {
//		            stmt.setInt(1, film.getId());
//		            stmt.setInt(2, newActorId);
//		            updateCount = stmt.executeUpdate();
//		          }
//		        }
//		        keys.close();
		      }
		    } 
//		    else {
//		      film = null;
//		    }
		    String sql2 = "SELECT id FROM category WHERE name = ?";
		    stmt = conn.prepareStatement(sql2);
		    stmt.setString(1, film.getCategory());
		    categoryId = stmt.executeQuery();
		    if (categoryId.next()) {
		         catId = categoryId.getInt("id");
		    }
		    String sql3 = "INSERT INTO film_category (film_id, category_id) VALUES (?,?)";
		    stmt = conn.prepareStatement(sql3);
		    stmt.setInt(1, newFilmId);
		    stmt.setInt(2, catId);
		    stmt.executeUpdate();
		    
//		    stmt.setString(9, film.getCategory());
		    conn.commit(); // COMMIT TRANSACTION
		    stmt.close();
		    conn.close();
		  } catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); }
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    throw new RuntimeException("Error inserting film " + film);
		  }
		return film;
	}

	@Override

	public boolean updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE film SET title=?, description=?, release_year=?, rental_rate=?, length=?, replacement_cost=?, rating=?, special_features=?, language_id=1 "
					+ " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setDouble(4, film.getRentalRate());
			stmt.setInt(5, film.getLength());
			stmt.setDouble(6, film.getReplaceCost());
			stmt.setString(7, film.getRating());
			stmt.setString(8, film.getFeatures());
			stmt.setInt(9, film.getFilmId());

			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				//redirect model to automate filmId into edit or delete in controller

				// Replace actor's film list
//		      sql = "DELETE FROM film_actor WHERE actor_id = ?";
//		      stmt = conn.prepareStatement(sql);
//		      stmt.setInt(1, film.getFilmId());
//		      updateCount = stmt.executeUpdate();
//		      sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//		      stmt = conn.prepareStatement(sql);
//		      for (Film film : actor.getFilms()) {
//		        stmt.setInt(1, film.getId());
//		        stmt.setInt(2, actor.getId());
//		        updateCount = stmt.executeUpdate();
//		      }
				conn.commit();// COMMIT TRANSACTION
				conn.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteFilm(Film film) {
		  Connection conn = null;
		  try {
		    conn = DriverManager.getConnection(URL, user, pass);
		    conn.setAutoCommit(false); // START TRANSACTION
		    String sql = "DELETE FROM film_category WHERE film_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, film.getFilmId());
		    int updateCount = stmt.executeUpdate();
		    sql = "DELETE FROM film WHERE id = ?";
		    stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, film.getFilmId());
		    updateCount = stmt.executeUpdate();
//		    sql = "DELETE FROM actor WHERE id = ?";
//		    stmt = conn.prepareStatement(sql);
//		    stmt.setInt(1, film.getFilmId());
//		    updateCount = stmt.executeUpdate();
		    conn.commit();             // COMMIT TRANSACTION
		  }
		  catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); }
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    return false;
		  }
		
		return true;
	}
}