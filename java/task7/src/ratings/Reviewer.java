package ratings;

public class Reviewer {

    /*
    This class will have a constructor that takes 1 parameter:
    1. A String representing the ID of the reviewer
    */
    private String ID;

    public Reviewer(String ID) {
        setReviewerID(ID);
    }

    /*
    Implement getter and setter methods for the constructor parameter named:
    1. getReviewerID
    2. setReviewerID
    */
    public String getReviewerID() {
        return this.ID;
    }

    public void setReviewerID(String ID) {
        this.ID = ID;
    }

    /*
    Implement a method named rateSong that takes an int as a parameter and returns
    a new Rating object with this reviewer's ID and the rating from the parameter.
    */
    public Rating rateSong(int input) {
        return new Rating(this.ID, input);
    }

}
