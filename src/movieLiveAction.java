import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// Define the class for Live-Action films
class LiveActionFilm extends movieAbstract {
    private List<String> actors;
    private int starRating;

    public LiveActionFilm(String name, String director, int year, List<String> actors) {
        super(name, director, year);
        this.actors = actors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setRating(int starRating,string comment) {
        this.starRating = starRating;
        this.comment = comment;
    }

    public String getRating() {
        return starRating + " stars out of 5 " +(comment!=null)?"Comment "+ comment : "";
    }
}