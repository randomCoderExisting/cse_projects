package ratings;

import ratings.datastructures.LinkedListNode;

import java.util.ArrayList;

public class Movie {
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
        if (this.movie_rating == null) {
            this.movie_rating = new LinkedListNode<>(rating, null);
        } else {
            if (!didReviewerRateMovie(rating.getReviewerID())) {
                this.movie_rating.append(rating);
            }
        }
    }

    public double bayesianAverageRating(int numOfExtra, double value) {
        return returnTotalAverage(this.movie_rating, numOfExtra, value);
    }

    /*
    this method will have 2 outputs:

    1. The total ratings of the input LinkedList
    2, The count of the input LinkedList
     */
    private double returnTotalAverage(LinkedListNode<Rating> input, int numOfExtra, double value) {
        double total = 0.0;
        int count = 0;
        if (input != null) {
            LinkedListNode<Rating> thisNode = input;
            while (thisNode != null) {
                total += thisNode.getValue().getRating();
                count ++;
                thisNode = thisNode.getNext();
            }
        }
        if ((count == 0) && (numOfExtra == 0)) {
            return 0.0;
        }
        double retVal = (total + (numOfExtra * value)) / (count + numOfExtra);
        return retVal;
    }

    public boolean didReviewerRateMovie(String reviewerID) {
        if (this.movie_rating != null) {
            LinkedListNode<Rating> thisNode = this.movie_rating;
            while (thisNode != null) {
                if (thisNode.getValue().getReviewerID().equals(reviewerID)) {
                    return true;
                }
                thisNode = thisNode.getNext();
            }
            return false;
        }
        return false;
    }

}
