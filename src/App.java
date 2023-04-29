import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        // Create a FilmManager instance
        FilmManager filmManager = new FilmManager();
        // Implement user interaction and call


        // Add a LiveActionFilm
        List<String> actors = Arrays.asList("Actor1", "Actor2");
        LiveActionFilm liveActionFilm = new LiveActionFilm("Film1", "Director1", 2020, actors);
        filmManager.addFilm(liveActionFilm);

    }
}