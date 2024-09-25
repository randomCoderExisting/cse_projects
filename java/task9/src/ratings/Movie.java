package ratings;

import ratings.datastructures.LinkedListNode;
import ratings.Ratable;
import java.util.ArrayList;

public class Movie extends Ratable {
    private String title;
    private ArrayList<String> casts;
    private LinkedListNode<Rating> movie_rating;

    public Movie(String title, ArrayList<String> casts) {
        this.title = title;
        this.casts = casts;
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<String> getCast() {
        return this.casts;
    }

    public void addRating(Rating rating) {
        super.addRating(rating);
    }

}
