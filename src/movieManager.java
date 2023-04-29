import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

class FilmManager {
    private Map<String, Film> films = new HashMap<>();

    // Add a new film
    public void addFilm(Film film) {
        films.put(film.getName(), film);
    }

    // Edit a film
    public void editFilm(String oldName, String newName, String director, int year, List<String> actorsOrAnimators) {
        Film film = films.get(oldName);
        if (film != null) {
            film.setName(newName);
            film.setDirector(director);
            film.setYear(year);
            if (film instanceof LiveActionFilm) {
                ((LiveActionFilm) film).setActors(actorsOrAnimators);
            } else if (film instanceof AnimatedFilm) {
                ((AnimatedFilm) film).setAnimators(actorsOrAnimators);
            }
        }
    }

    // Delete a film
    public void deleteFilm(String name) {
        films.remove(name);
    }

    // Add a rating to a film
    public void addRating(String name, int rating) {
        Film film = films.get(name);
        if (film instanceof LiveActionFilm) {
            ((LiveActionFilm) film).setStarRating(rating);
        } else if (film instanceof AnimatedFilm) {
            ((AnimatedFilm) film).setScoreRating(rating);
        }
    }

    // Display all films
    public void displayFilms() {
        for (Film film : films.values()) {
            System.out.println(film.getName() + ", " + film.getDirector() + ", " + film.getYear() + ", " + film.getRating());
        }
    }

    // Search for a film by name
    public Film searchFilm(String name) {
        return films.get(name);
    }

    // Display actors or animators with multiple films
    public void displayMultiFilmActorsOrAnimators() {
        // Implement this method based on your requirements
    }

    // Display films with a specific actor or animator
    public void displayFilmsWithActorOrAnimator(String actorOrAnimator) {
        // Implement this method based on your requirements
    }

    // Save a film to a file
    public void saveFilmToFile(String name) {
        // Implement this method based on your requirements
    }

    // Load film information from a file
    public Film loadFilmFromFile(String filePath) {
        // Implement this method based on your requirements
        return null;
    }

    // Save all films to a SQL database
    public void saveFilmsToDatabase() {
        // Implement this method based on your requirements
    }

    // Load all films from a SQL database
    public void loadFilmsFromDatabase() {
        // Implement this method based on your requirements
    }
}