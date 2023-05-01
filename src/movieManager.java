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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        
        if(films.remove(name)==null)
        throw new NullPointerException("Film not found");
    }

    public void addRating(String name, int score) {
        addRating(name, score, null);
    }
    
    public void addRating(String name, int score, String comment) {
        movieAbstract film = films.get(name);
        if (film != null && (((film instanceof AnimatedFilm)&& score>=0 && score<=10) || ((film instanceof LiveActionFilm)&& score>=0 && score<=5))) {
            if (comment != "") {
                film.addRating(score, comment);
            } else {
                film.addRating(score);
            }
        }else{
            throw new IllegalArgumentException("Invalid rating score or movie not found");
        }
    }

    // Display all films
    public void displayFilms() {
        for (movieAbstract film : films.values()) {
            List<String> actorsOrAnimators = (film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators();
            int age = (film instanceof AnimatedFilm) ? ((AnimatedFilm) film).getAgeRating(): 0;
            System.out.println("Name: "+film.getName() + ", Director: " + film.getDirector() + ", Year of production: " + film.getYear() + ", Actors or Animators" + actorsOrAnimators + ((age > 0) ? ", Recommended age of viewer: " + age : ""));
        }
    }

    // Search for a film by name
    public Film searchFilm(String name) {
        if(films.get(name)==null)
        throw new NullPointerException("Film not found");
        return films.get(name);
    }

    // Search for a film by name and then return information about it
    public void searchForFilm(String name) {
        movieAbstract film = films.get(name);
        if (film == null) {
            throw new NullPointerException("Film not found");
        } else {
            List<String> actorsOrAnimators = (film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators();
            int age = (film instanceof AnimatedFilm) ? ((AnimatedFilm) film).getAgeRating() : 0;
    
            // Sort the ratings in descending order based on the score
            List<Rating> sortedRatings = new ArrayList<>(film.getRating());
            sortedRatings.sort((r1, r2) -> Integer.compare(r2.getScore(),r1.getScore()));
    
            // Display the ratings
            StringBuilder ratingsInfo = new StringBuilder();
            for (Rating rating : sortedRatings) {
                ratingsInfo.append("Score: ").append(rating.getScore()).append(", Comment: ").append(rating.getComment()).append("\n");
            }
    
            String filmInfo = "Name: " + film.getName() + ", Director: " + film.getDirector() + ", Year of production: " + film.getYear()
                    + ", Actors or Animators: " + actorsOrAnimators
                    + ((age > 0) ? ", Recommended age of viewer: " + age : "")
                    + "\nViewer Ratings:\n" + ratingsInfo;
            System.out.println(filmInfo);
        }
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



    public void saveFilmToFile(String name) {
     
            movieAbstract film = films.get(name);
            String fileName = name + ".txt";
            File file = new File(fileName);

            try (FileWriter fileWriter = new FileWriter(file, false)) {
                fileWriter.write("name:" + film.getName() + "\n");
                fileWriter.write("director:" + film.getDirector() + "\n");
                fileWriter.write("year:" + film.getYear() + "\n");

                if (film instanceof LiveActionFilm) {
                    fileWriter.write("type:LiveActionFilm\n");
                    fileWriter.write("actors:" + String.join(",", ((LiveActionFilm) film).getActors()) + "\n");
                } else {
                    fileWriter.write("type:AnimatedFilm\n");
                    fileWriter.write("animators:" + String.join(",", ((AnimatedFilm) film).getAnimators()) + "\n");
                    fileWriter.write("ageRating:" + ((AnimatedFilm) film).getAgeRating() + "\n");
                }
                for (Rating rating : film.getRating()) {
                    fileWriter.write("rating:");
                    fileWriter.write(Integer.toString(rating.getScore()));
                    if (rating.getComment() != null) {
                        fileWriter.write(":");
                        fileWriter.write(rating.getComment());
                    }
                    fileWriter.write("\n");
                }
        
                System.out.println("Film saved to file: " + fileName);
            } catch (IOException e) {
                System.out.println("Error saving film to file: " + e.getMessage());
            }
        
    }
    //Write a function that will load a film from a file
    public void loadFilmFromFile(String name) {
        File file = new File(name+".txt");
        if (!file.exists()) {
            System.out.println("File not found.");
        
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Map<String, String> filmData = new HashMap<>();
            List<Rating> ratingsList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(":");
                if (keyValue.length >= 2) {
                    if (keyValue[0].equals("rating")) {
                        int score = Integer.parseInt(keyValue[1]);
                        String comment = keyValue.length == 3 ? keyValue[2] : null;
                        ratingsList.add(new Rating(score, comment));
                    } else {
                        filmData.put(keyValue[0], keyValue[1]);
                    }
                }
            }

            if (filmData.containsKey("name") && filmData.containsKey("director") && filmData.containsKey("year") && filmData.containsKey("type")) {
               name = filmData.get("name");
                String director = filmData.get("director");
                int year = Integer.parseInt(filmData.get("year"));
                String type = filmData.get("type");
    
                movieAbstract film;
                if (type.equals("LiveActionFilm")) {
                    List<String> actors = Arrays.asList(filmData.get("actors").split(","));
                    film = new LiveActionFilm(name, director, year, actors);
                } else {
                    List<String> animators = Arrays.asList(filmData.get("animators").split(","));
                    int ageRating = Integer.parseInt(filmData.get("ageRating"));
                    film = new AnimatedFilm(name, director, year, animators, ageRating);
                }

                // Load ratings
                for (Rating rating : ratingsList) {
                    film.addRating(rating.getScore(), rating.getComment());
                }
                films.put(film.getName(), film);
             
            } else {
                System.out.println("Error: Incomplete film data in file.");
         
            }
        } catch (IOException e) {
            System.out.println("Error loading film from file: " + e.getMessage());
           
        }
    
    }
   
    public void saveFilmsToDatabase() {
        // Implement this method based on your requirements
       // String insertUser = "INSERT INTO movies2 " + "(Name,Director,Year,Rating)" + "VALUES(?,?,?,?)";
        Connection connection=null;
        try {
           connection = DriverManager.getConnection("jdbc:sqlite:mydbmovies.db");
            // použití connection pro práci s databází

            String insertSQL1 = "INSERT INTO movies3 (Name,Director,Year,Rating,Actors) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement prStmt = connection.prepareStatement(insertSQL1)) {
                for (movieAbstract film : films.values()) {
                    List<String> actorsOrAnimators = (film instanceof LiveActionFilm) ? ((LiveActionFilm) film).getActors() : ((AnimatedFilm) film).getAnimators();
                   int age = (film instanceof AnimatedFilm) ? ((AnimatedFilm) film).getAgeRating(): 0;
                   System.out.println("Name: "+film.getName() + ", Director: " + film.getDirector() + ", Year of production: " + film.getYear() + ", Movie rating: "
                    + film.getRating() + ", Actors or Animators" + actorsOrAnimators + ((age > 0) ? ", Recommended age of viewer: " + age : ""));

            prStmt.setString(1, film.getName());
            prStmt.setString(2, film.getDirector());
            prStmt.setInt(3, film.getYear());
            prStmt.setString(4, film.getRating());
            prStmt.setString(5,"nevimjakdal" );
            prStmt.executeUpdate();
                }
            System.out.println("New user been added into database!");
            } catch (SQLException e) {
              System.out.println("Did not work");
              // e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Error connecting to database");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database connection");
                    e.printStackTrace();
                }
            }
        }
    }
    // Load all films from a SQL database
    public void loadFilmsFromDatabase() {
        // Implement this method based on your requirements
        Connection connection=null;
        try {
           connection = DriverManager.getConnection("jdbc:sqlite:mydbmovies.db");
            // použití connection pro práci s databází
            try (Statement prStmt = connection.createStatement();
            ResultSet rs = prStmt.executeQuery("SELECT * FROM movies3")) {
              while (rs.next()) {
    
                  System.out.println(rs.getString("Name") + ", "
                     + rs.getString("Director") + ", " + rs.getInt("Year") + ", " + rs.getInt("Rating")+ ", " + rs.getString("Actors"));
                }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        } catch (SQLException e) {
            System.err.println("Error connecting to database");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database connection");
                    e.printStackTrace();
                }
            }
        }

    }
}


