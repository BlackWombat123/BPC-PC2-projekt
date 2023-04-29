
// Define the class for Animated films
class AnimatedFilm extends movieAbstract {
    private List<String> animators;
    private int ageRating;
    private int scoreRating;

    public AnimatedFilm(String name, String director, int year, List<String> animators, int ageRating) {
        super(name, director, year);
        this.animators = animators;
        this.ageRating = ageRating;
    }

    public List<String> getAnimators() {
        return animators;
    }

    public void setAnimators(List<String> animators) {
        this.animators = animators;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public int getScoreRating() {
        return scoreRating;
    }

    public void setRating(int scoreRating, String comment) {
        this.scoreRating = scoreRating;
        this.comment = comment;
    }

    public String getRating() {
        return scoreRating + "/10 " +(comment!=null)?"Comment "+ comment : "";
    }
}