package ratings;

public class Rating {

    /*
    This class will have a constructor that takes 2 parameters in this order:
    1. A String representing the ID of the reviewer who gave the rating
    2. An int representing the rating that the reviewer gave
    */
    private String ID;
    private int rating;

    public Rating(String ID, int rating) {
        setReviewerID(ID);
        setRating(rating);
    }

    /*
    Implement getter and setter methods for the two constructor parameters named:
    1. getReviewerID
    2. setReviewerID
    3. getRating
    4. setRating
    */
    public String getReviewerID() {
        return this.ID;
    }

    public void setReviewerID(String ID) {
        this.ID = ID;
    }

    public int getRating() {
        return this.rating;
    }

    /*
    Ratings must be in the range 1-5. If someone calls setRatings with an invalid rating,
    the rating should be set to -1 to indicate that an error has occurred.
    (eg. setRating(100) should result in the rating being set to -1)
    */
    public void setRating(int rating) {
        if ((0 < rating) && (rating < 6)) {
            this.rating = rating;
        } else {
            this.rating = -1;
        }
    }

}
