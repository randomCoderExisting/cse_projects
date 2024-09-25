package tests;

import org.junit.Test;
import ratings.FileReader;
import ratings.Rating;
import ratings.Song;
import ratings.datastructures.LinkedListNode;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class TestClasses3 {

    /*
    Test a static method named "readSongs"

    takes a string of the filename & return an arrayList that matches the following format:
    "songID,artist,title,reviewerID,rating"

    return an ArrayList of Songs containing all information

    if a song has been rated more than once, only 1 object can be returned,
    but all ratings should be contained.

    The array will be in any order, the test must accept all orders with same content.

    The ratings of each song must be in order of when they first appeared in file.
    */

    @Test
    public void testReadSongs() {

        //return empty ArrayList if the input filename DNE.
        ArrayList<Song> computed = FileReader.readSongs("data/invalid.csv");
        assertTrue(computed.isEmpty());

        computed = FileReader.readSongs("data/ratings.csv");

        ArrayList<Song> expected = new ArrayList<>();

        //Song has only been added once! 31W5EY0aAly4Qieq6OFu6I,A Boogie with Da Hoodie,Artist,204,5
        Song song1 = new Song("Artist", "A Boogie with Da Hoodie", "31W5EY0aAly4Qieq6OFu6I");
        song1.addRating(new Rating("204", 5));

        assertTrue(compareArray(computed, song1));

        Song song2 = new Song("Maple Syrup", "The Backseat Lovers", "4MXE6VCvTsQitHWrAxj7Kg");
        song2.addRating(new Rating("552", 5));
        song2.addRating(new Rating("173", 5));
        song2.addRating(new Rating("47", 3));
        song2.addRating(new Rating("255", 4));
        song2.addRating(new Rating("336", 5));
        song2.addRating(new Rating("214", 3));
        song2.addRating(new Rating("352", 3));
        song2.addRating(new Rating("653", 4));
        song2.addRating(new Rating("331", 4));
        song2.addRating(new Rating("211", 4));
        song2.addRating(new Rating("186", 5));
        song2.addRating(new Rating("520", 3));

        assertTrue(compareArray(computed, song2));
    }

    private boolean compareArray(ArrayList<Song> computed, Song expected) {
        for (int i = 0; i < computed.size(); i++) {
            if (computed.get(i).getSongID().equals(expected.getSongID())) {
                return compareContent(computed.get(i), expected);
            }
        }
        return false;
    }

    private boolean compareContent(Song computed, Song expected) {
        if (!computed.getSongID().equals(expected.getSongID())) {
            return false;
        }
        if (!computed.getArtist().equals(expected.getArtist())) {
            return false;
        }
        if (!computed.getTitle().equals(expected.getTitle())) {
            return false;
        }
        return compareRating(computed.getRatings(), expected.getRatings());
    }

    private boolean compareRating(LinkedListNode<Rating> computed, LinkedListNode<Rating> expected) {
        LinkedListNode<Rating> Node1 = computed;
        LinkedListNode<Rating> Node2 = expected;
        if (Node1.size() != Node2.size()) {
            return false;
        }
        while (Node1 != null) {
            if (!Node1.getValue().getReviewerID().equals(Node2.getValue().getReviewerID())) {
                return false;
            }
            if (Node1.getValue().getRating() != Node2.getValue().getRating()) {
                return false;
            }
            Node1 = Node1.getNext();
            Node2 = Node2.getNext();
        }
        return true;
    }

}
