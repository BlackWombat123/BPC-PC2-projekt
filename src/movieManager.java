import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

class FilmManager {
    private Map<String, movieAbstract> films = new HashMap<>();

    // Add a new film
    public void addFilm(movieAbstract film) {

        films.put(film.getName(), film);
    }
    public String movieType(String name){

        movieAbstract film = films.get(name);
        return ((film instanceof LiveActionFilm)? "Live-Action" : "Animated");

    }
    // Edit a film
    public void editFilm(String oldName, String newName, String director, int year, List<String> actorsOrAnimators, int ageRating) {
        movieAbstract film = films.get(oldName);
        if (film != null) {
            if(newName !="")
            film.setName(newName);

            if(director !="")
            film.setDirector(director);

            if(year !=0)
            film.setYear(year);

            if (film instanceof LiveActionFilm && !actorsOrAnimators.get(0).isEmpty()) {
                ((LiveActionFilm) film).setActors(actorsOrAnimators);
            } else if (film instanceof AnimatedFilm  && !actorsOrAnimators.get(0).isEmpty()) {
                ((AnimatedFilm) film).setAnimators(actorsOrAnimators);
              
            }
            if(ageRating>=0)
            ((AnimatedFilm) film).setAgeRating(ageRating);
        }
    }

    // Delete a film
    public void deleteFilm(String name) {
        films.remove(name);
    }

    // Add a rating to a film
    public void addRating(String name, int rating,String comment) {
        movieAbstract film = films.get(name);

        if (film instanceof LiveActionFilm) {
          
            if(comment!=null){
                ((LiveActionFilm) film).setRating(rating,comment);
            }else{
                ((LiveActionFilm) film).setRating(rating,comment);
            }
            
        } else if (film instanceof AnimatedFilm) {
          
            if(comment!=null){
                ((AnimatedFilm) film).setRating(rating,comment);
            }else{
                ((AnimatedFilm) film).setRating(rating,comment);
            }
        }
    }

    // Display all films
    public void displayFilms() {
        for (movieAbstract film : films.values()) {
            List<String> actorsOrAnimators = (film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators();
            int age = (film instanceof AnimatedFilm) ? ((AnimatedFilm) film).getAgeRating(): 0;
            System.out.println("Name: "+film.getName() + ", Director: " + film.getDirector() + ", Year of production: " + film.getYear() + ", Movie rating: "
             + film.getRating() + ", Actors or Animators" + actorsOrAnimators + ((age > 0) ? ", Recommended age of viewer: " + age : ""));
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


