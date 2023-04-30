import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    // Search for a film by name and then return information about it like in function search for actor
    public Film searchForFilm(String name){
        movieAbstract film = films.get(name);
        List<String> actorsOrAnimators = (film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators();
        int age = (film instanceof AnimatedFilm) ? ((AnimatedFilm) film).getAgeRating(): 0;
        String filmInfo="Name: "+film.getName() + ", Director: " + film.getDirector() + ", Year of production: " + film.getYear() + ", Movie rating: "
        + film.getRating() + ", Actors or Animators" + actorsOrAnimators + ((age > 0) ? ", Recommended age of viewer: " + age : "");
        System.out.println(filmInfo);
        return film;
       
    }

    //Search all the movies for specific actor and display all the movies hes been in
    public void searchForActor(String name){
        for (movieAbstract film : films.values()) {
            List<String> actorsOrAnimators = (film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators();
            if(actorsOrAnimators.contains(name)){
                System.out.println("Name: "+film.getName() + ", Director: " + film.getDirector() + ", Year of production: " + film.getYear() + ", Actors or Animators" + actorsOrAnimators);
            }
        }
    }

    // Display actors or animators with multiple films
    public void displayMultiFilmActorsOrAnimators() {
        // Implement this method based on your requirements
        Map<String, List<String>> actorsOrAnimatorsMovies = new HashMap<>();
        for (movieAbstract film : films.values()) {
            List<String> actorsOrAnimators = ((film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators());
            for (String actor : actorsOrAnimators){
                actorsOrAnimatorsMovies.computeIfAbsent(actor, k -> new ArrayList<>()).add(film.getName());
            }
        }
        for(String actor : actorsOrAnimatorsMovies.keySet()){
            if(actorsOrAnimatorsMovies.get(actor).size() > 1){
                System.out.println(actor + " has been in the following movies: " + actorsOrAnimatorsMovies.get(actor));
            }
        }
      
    }



    // Save a film to a file
    public void saveFilmToFile(String name) {
        // Implement this method based on your requirements
       // Film movie=searchForFilm(name);
       
        String fileName = "movieList.txt";
        File file = new File(fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("Soubor byl úspěšně vytvořen.");
            } else {
                System.out.println("File already exist.");
            }
                try {
          movieAbstract film = films.get(name);
        List<String> actorsOrAnimators = (film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators();
        int age = (film instanceof AnimatedFilm) ? ((AnimatedFilm) film).getAgeRating(): 0;
     
                    FileWriter fileWriter = new FileWriter(fileName, false);
        
                    fileWriter.write("Name: "+film.getName() + ", Director: " + film.getDirector() + ", Year of production: " + film.getYear() + ", Movie rating: "
                    + film.getRating() + ", Actors or Animators" + actorsOrAnimators + ((age > 0) ? ", Recommended age of viewer: " + age : ""));
                    fileWriter.close();
        
                   
                } catch (IOException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Load film information from a file
    public void loadFilmFromFile() {
        // Implement this method based on your requirements
        String fileName = "movieList.txt";
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("File could not be read.");
            e.printStackTrace();
        }
        
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


