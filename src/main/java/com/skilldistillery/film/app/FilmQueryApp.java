package com.skilldistillery.film.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.film.database.FilmDAO;
import com.skilldistillery.film.database.FilmDAOImpl;
import com.skilldistillery.film.entities.Film;


public class FilmQueryApp {

	FilmDAO db = new FilmDAOImpl();

	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		startUserInterface();
		input.close();
	}

	private void startUserInterface() {
		menu();
	}

	public void menu() {
		int choice = 0;
		int id = 0;
		String keyword = "";
		boolean flag = true;

		System.out.println("Welcome to Movie Hut.\n");
		do {
			try {
				System.out.println("What would you like to do? ");
				System.out.println("1. Look up a film by its id" + 
								   "\n2. Look up a film by a search keyword" +
								   "\n3. Exit the application");
				choice = Integer.parseInt(input.nextLine());

				if (choice > 4 || choice < 1) {
					System.out.println("That's not a valid option.\n");
					continue;
				}
				switch (choice) {
				case 1:
					System.out.println("Enter the film ID: ");
					id = Integer.parseInt(input.nextLine());
					Film result = db.findFilmById(id);
					if (result != null) {
						System.out.println(result + "\n");
						subMenu(result);
					} else {
						System.out.println("Film not found.\n");
					}
					break;
				case 2:
					System.out.println("Enter a keyword: ");
					keyword = input.nextLine();
					List<Film> keyFilms = db.findFilmByKeyword(keyword);
					System.out.println("There are " + keyFilms.size() + " films that match your search: \n");
					for (Film film : keyFilms) {
						System.out.println(film + "\n");
					}
					if (keyFilms.size() != 0) {
						System.out.println(
								"\nTo view more details for a film on this list, search by the film ID and select \"See all film details\".\n\n");
					}
					break;
				case 3:
					System.out.println("You've ended your search. Buh-bye!");
					flag = false;
					break;
				}
			} catch (Exception e) {
				System.out.println("That's not a valid option.\n");
			}
		} while (flag);
	}

	public void subMenu(Film selectedFilm) {
		int selection = 0;
		boolean flag = true;
		
		do {
			try {
				System.out.println("Would you like to: ");
				System.out.println("1. See all film details" + "\n2. Return to main menu");
				
				selection = Integer.parseInt(input.nextLine());

				if (selection > 2 || selection < 1) {
					System.out.println("That's not a valid option.\n");
					continue;
				} 
				else if (selection == 1) {
					System.out.println(selectedFilm.fullPrint());
				}
				flag = false;
				break;
			} catch (Exception e) {
				System.out.println("That's not a valid option.\n");
			}
		} while (flag);
	}
}