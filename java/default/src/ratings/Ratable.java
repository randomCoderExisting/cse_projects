package ratings;

import ratings.datastructures.LinkedListNode;

public class Ratable {

    private String title;
    private LinkedListNode<Rating> rating;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*
    A public method named addRating that takes a [reference to a] Rating
    object as a parameter and returns void

    This method will be called when a Reviewer rates this Song. You should
    store all added Ratings in this object. You may add any additional
    instance variables that you'd like.
    */

    public void addRating(Rating rating) {
        if (this.rating == null) {
            this.rating = new LinkedListNode<>(rating, null);
        } else {
            if (!didReviewerRateSong(rating.getReviewerID())) {
                this.rating.append(rating);
            }
        }
    }

    /*
    A public Method named getRatings that takes no parameters and returns
    a LinkedListNode of Ratings

    This method returns the head of a Linked List containing all Ratings
    that have been added to this Song.

    The returned Linked List must return the Ratings in the order in which
    they were added. For example, the first Rating that was added must be
    the first value in the list.
    */
    public LinkedListNode<Rating> getRatings() {
        return this.rating;
    }

    /*
    A public method named averageRating that takes no parameters and returns a
    double

    This method return the average of all the Rating for this Song

    Technically, ratings of -1 should be ignored, but I forgot to include a test
    for that, so you'll get away with it if you don't ignore them.
    */
    public double averageRating() {
        if (this.rating != null) {
            double total = this.rating.getValue().getRating();
            int i = 1;
            LinkedListNode<Rating> nextNode = this.rating.getNext();
            while (nextNode != null) {
                total += nextNode.getValue().getRating();
                i += 1;
                nextNode = nextNode.getNext();
            }
            return total / i;
        }
        return 0.0;
    }

    /*
    A public method named didReviewerRateSong that takes a String representing the
    ID of a reviewer and returns a boolean

    This method returns true if the reviewer with the given ID has rated this Song,
    false otherwise.

    Update the addRating method to prevent a Reviewer from rating a Song multiple times

    You should call your didReviewerRateSong method from your addRating method to check
    if the reviewer rated this Song yet. If it returns true, don't add the new Rating
    to your Linked List
    */
    public boolean didReviewerRateSong(String reviewerID) {
        if (this.rating != null) {
            LinkedListNode<Rating> thisNode = this.rating;
            while (thisNode != null) {
                if (thisNode.getValue().getReviewerID().equals(reviewerID)) {
                    return true;
                }
                thisNode = thisNode.getNext();
            }
        }
        return false;
    }

    /*
    A public method named removeRatingByReviewer that takes a Reviewer as a parameter and
    returns void

    This method will remove any Rating made for this Song by the given Reviewer

    Note that since you are preventing multiple Rating from a Reviewer in your addRating
    method, you can assume that the Reviewer has rated this Song at most once.
    */
    public void removeRatingByReviewer(Reviewer reviewer) {
        if (this.rating != null) {

            //check the first node in the linked list
            if (this.rating.getValue().getReviewerID().equals(reviewer.getReviewerID())) {
                this.rating = this.rating.getNext();
            }

            //check the other nodes in the linked list
            if (this.rating != null) {
                removeNode(reviewer.getReviewerID(), this.rating);
            }
        }
    }

    private void removeNode(String reviewerID, LinkedListNode<Rating> thisNode) {
        if (thisNode.getNext() != null) {
            LinkedListNode<Rating> nextNode = thisNode.getNext();
            if (nextNode.getValue().getReviewerID().equals(reviewerID)) {
                thisNode.setNext(nextNode.getNext());
            } else {
                removeNode(reviewerID, thisNode.getNext());
            }
        }
    }

    /*
       this method will have 2 outputs:

       1. The total ratings of the input LinkedList
       2, The count of the input LinkedList
        */

    public double bayesianAverageRating(int numOfExtra, int value) {
        double total = 0.0;
        int count = 0;
        if (this.rating != null) {
            LinkedListNode<Rating> thisNode = this.rating;
            while (thisNode != null) {
                total += thisNode.getValue().getRating();
                count ++;
                thisNode = thisNode.getNext();
            }
        }
        if ((count + numOfExtra) == 0) {
            return 0.0;
        }
        return (total + (numOfExtra * value)) / (count + numOfExtra);
    }

}
