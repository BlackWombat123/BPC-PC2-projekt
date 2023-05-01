class Rating {
    public int score;
    public String comment;

    public Rating(int score) {
        this.score = score;
        this.comment = null;
    }

    public Rating(int score, String comment) {
        this.score = score;
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return "Rating: "+score + ((comment != null) ? " Comment: " + comment : "");
    }
}
