package ratings;

import ratings.datastructures.LinkedListNode;

import java.util.ArrayList;

public class Song {

    /*
This class will have a constructor that takes 3 parameters in this order:
A String representing the title of the Song
A String representing the artist of the Song
A String representing the Songs ID
*/
    private String title;
    private String artist;
    private String song_id;

    private LinkedListNode<Rating> song_rating;

    public Song(String title, String artist, String song_id) {
        setTitle(title);
        setArtist(artist);
        setSongID(song_id);
    }

    /*
Implement getter and setter methods for the three constructor parameters named:
1. getTitle
2. setTitle
3. getArtist
4. setArtist
5. getSongID <-- Note that both characters in ID are capital
6. setSongID
    */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongID() {
        return this.song_id;
    }

    public void setSongID(String song_id) {
        this.song_id = song_id;
    }

    /**
     Task 3
     **/

    /*
    A public method named addRating that takes a [reference to a] Rating
    object as a parameter and returns void

    This method will be called when a Reviewer rates this Song. You should
    store all added Ratings in this object. You may add any additional
    instance variables that you'd like.
    */
    public void addRating(Rating rating) {
        if (this.song_rating == null) {
            this.song_rating = new LinkedListNode<>(rating, null);
        } else {
            if (!didReviewerRateSong(rating.getReviewerID())) {
                this.song_rating.append(rating);
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
        return this.song_rating;
    }

    /*
    A public method named averageRating that takes no parameters and returns a
    double

    This method return the average of all the Rating for this Song

    Technically, ratings of -1 should be ignored, but I forgot to include a test
    for that, so you'll get away with it if you don't ignore them.
    */
    public double averageRating() {
        if (this.song_rating != null) {
            double total = this.song_rating.getValue().getRating();
            int i = 1;
            LinkedListNode<Rating> nextNode = this.song_rating.getNext();
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
        if (this.song_rating == null) {
            return false;
        }
        if (this.song_rating.getValue().getReviewerID().equals(reviewerID)) {
            return true;
        }
        LinkedListNode<Rating> nextNode = this.song_rating.getNext();
        while (nextNode != null) {
            if (nextNode.getValue().getReviewerID().equals(reviewerID)) {
                return true;
            } else {
                nextNode = nextNode.getNext();
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
        if (this.song_rating != null) {

            //check the first node in the linked list
            if (this.song_rating.getValue().getReviewerID().equals(reviewer.getReviewerID())) {
                this.song_rating = this.song_rating.getNext();
            }

            //check the other nodes in the linked list
            if (this.song_rating != null) {
                removeNode(reviewer.getReviewerID(), this.song_rating);
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

    private void print(LinkedListNode<Rating> input) {
        if (input != null) {
            System.out.println(input.getValue().getReviewerID() + ": " + input.getValue().getRating() + ", ");
            print(input.getNext());
        } else {
            System.out.println("null");
        }
    }

}
