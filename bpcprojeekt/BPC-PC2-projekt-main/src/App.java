import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Create a FilmManager instance
        Scanner scanner = new Scanner(System.in);
        FilmManager filmManager = new FilmManager();
        int choice;
        List<String> actors1 = Arrays.asList("Actor1", "Actor2","Animator1");
        LiveActionFilm liveActionFilm = new LiveActionFilm("Film1", "Director1", 2020, actors1);
        filmManager.addFilm(liveActionFilm);

        // Add an AnimatedFilm
        List<String> animators1 = Arrays.asList("Animator1", "Animator2","Actor1");
        AnimatedFilm animatedFilm = new AnimatedFilm("Film2", "Director2", 2021, animators1, 10);
        filmManager.addFilm(animatedFilm);

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Film");
            System.out.println("2. Edit Film");
            System.out.println("3. Delete Film");
            System.out.println("4. Add Rating");
            System.out.println("5. Display Films");
            System.out.println("6. Search Film");
            System.out.println("7. Search For actor or animator");
            System.out.println("8. Display animators or actors that played in multiple movies");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            switch (choice) {
                case 1:
                    // Add Film
                    try{
                    System.out.println("Enter the type of film (1 - Live Action, 2 - Animated): ");
                    int type = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline

                    System.out.println("Enter the film name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter the director: ");
                    String director = scanner.nextLine();

                    System.out.println("Enter the year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline

                    if (type == 1) {
                        System.out.println("Enter the actors (comma-separated): ");
                        String actorsInput = scanner.nextLine();
                        List<String> actors = Arrays.asList(actorsInput.split(","));
                        filmManager.addFilm(new LiveActionFilm(name, director, year, actors));
                    } else if (type == 2) {
                        System.out.println("Enter the animators (comma-separated): ");
                        String animatorsInput = scanner.nextLine();
                        List<String> animators = Arrays.asList(animatorsInput.split(","));

                        System.out.println("Enter the recommended age: ");
                        int recommendedAge = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline

                        filmManager.addFilm(new AnimatedFilm(name, director, year, animators, recommendedAge));
                    } else {
                        System.out.println("Invalid film type.");
                    } } catch (Exception e) {
                        System.out.println("Error adding film: " + e.getMessage());
                    }
                    // Implement the logic for adding a film
                    break;
                case 2:
                try {
                    System.out.println("Enter the current film name: ");
                    String currentName = scanner.nextLine();



                    System.out.println("If you want to keep the old value --- Press enter");
                    System.out.println("Enter the new film name: ");
                    String newName = scanner.nextLine();               

                    System.out.println("Enter the new director: ");
                    String newDirector = scanner.nextLine();

                    System.out.println("Enter the new year: ");
                    int newYear = scanner.nextInt();

                 
                  

                    scanner.nextLine(); // Consume the newline
                    String newActorsOrAnimatorsInput = "";
                    int recommendedAge = -1;
                    if(filmManager.movieType(currentName) == "Animated"){
                        System.out.println("Enter the new animators (comma-separated): ");
                        newActorsOrAnimatorsInput = scanner.nextLine();

                        System.out.println("Enter the new recommended age: ");
                        recommendedAge =  scanner.nextInt();

                    }else{
                        System.out.println("Enter the new actors (comma-separated): ");
                        newActorsOrAnimatorsInput = scanner.nextLine();
                    }
 
                    List<String> newActorsOrAnimators = Arrays.asList(newActorsOrAnimatorsInput.split(",\\s*"));
                    filmManager.editFilm(currentName, newName, newDirector, newYear, newActorsOrAnimators,recommendedAge);

                } catch (Exception e) {
                    System.out.println("Error editing film: " + e.getMessage());
                }
                    break;
                case 3:
                try {
                    System.out.println("Enter the film name: ");
                    String name = scanner.nextLine();

                    filmManager.deleteFilm(name);
                    System.out.println("Film deleted successfully.");
                  
                } catch (Exception e) {
                    System.out.println("Error deleting film: " + e.getMessage());
                }
                    break;
                    
                case 4:
                    System.out.println("Enter the name of the film:");
                    String filmName = scanner.nextLine();

                    System.out.println("Enter the rating value:");
                    int ratingValue = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter a comment (optional, press Enter to skip):");
                    String comment = scanner.nextLine();

                    filmManager.addRating(filmName, ratingValue,comment);
                    // Add Rating
                    // Implement the logic for adding a rating
                    break;
                case 5:
                    // Display Films
                    filmManager.displayFilms();
                    break;
                case 6:
                    // Search Film
                    System.out.println("Enter the name of the film:");
                    String filmName1 = scanner.nextLine();
                    filmManager.searchForFilm(filmName1);
                    break;
                case 7:
                    // Search Film
                    System.out.println("Enter the name of the actor:");
                    String name = scanner.nextLine();
                    filmManager.searchForActor(name);
                    break;
                case 8:
                    filmManager.displayMultiFilmActorsOrAnimators();
                    break;
                case 9:
                //save file
                System.out.println("Enter the name of the film:");
                    String filmName3 = scanner.nextLine();
                    filmManager.saveFilmToFile(filmName3);
                    
                    break;
                case 10:
                //load file
                filmManager.loadFilmFromFile();
                break;
                case 11:
                    // Exit
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 12);

    }
}