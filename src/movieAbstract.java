
import java.util.List;
import java.util.ArrayList;

abstract class movieAbstract implements Film  {
    private String name;
    private String director;
    private int year;
    private List<Rating> ratings;


    public movieAbstract(String name, String director, int year) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.ratings = new ArrayList<>();
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addRating(int score) {
        ratings.add(new Rating(score));
    }
    
    public void addRating(int score, String comment) {
        ratings.add(new Rating(score, comment));
    }

    public List<Rating> getRating() {
        return ratings;
    }
   
    
}
