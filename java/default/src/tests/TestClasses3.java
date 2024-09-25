package tests;

import org.junit.Test;
import ratings.*;
import ratings.datastructures.LinkedListNode;
import static org.junit.Assert.assertTrue;
import ratings.MediaLibrary;

import java.util.ArrayList;
import java.util.Arrays;

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
        while (Node1 != null && Node2 != null) {
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

    @Test
    public void testReadMovieRatings() {
        //reject invalid filenames
        ArrayList<Movie> getData = new ArrayList<>();

        ArrayList<Movie> computed = FileReader.readMovieRatings(getData, "hello.csv");
        assertTrue(computed.isEmpty());

        //test normal test case
        getData = new ArrayList<>();

        ArrayList<Movie> movieData = FileReader.readMovies("data/movie_ratings.csv");
        getData.add(findMovie("Sicko", movieData));
        getData.add(findMovie("Ronin", movieData));

        computed = FileReader.readMovieRatings(getData, "data/movie_ratings.csv");

        LinkedListNode<Rating> expectedRating1 = new LinkedListNode<>(new Rating("83", 4), null);
        expectedRating1.append(new Rating("119", 4));
        expectedRating1.append(new Rating("125", 5));
        expectedRating1.append(new Rating("130", 4));
        expectedRating1.append(new Rating("185", 3));
        expectedRating1.append(new Rating("193", 5));
        expectedRating1.append(new Rating("195", 3));
        expectedRating1.append(new Rating("212", 3));
        expectedRating1.append(new Rating("226", 3));
        expectedRating1.append(new Rating("245", 5));
        expectedRating1.append(new Rating("358", 5));
        expectedRating1.append(new Rating("380", 4));
        expectedRating1.append(new Rating("388", 4));
        expectedRating1.append(new Rating("414", 3));
        expectedRating1.append(new Rating("417", 4));
        expectedRating1.append(new Rating("422", 5));
        expectedRating1.append(new Rating("452", 4));
        expectedRating1.append(new Rating("463", 3));
        expectedRating1.append(new Rating("472", 5));
        expectedRating1.append(new Rating("509", 3));
        expectedRating1.append(new Rating("510", 5));
        expectedRating1.append(new Rating("564", 3));
        expectedRating1.append(new Rating("565", 5));
        expectedRating1.append(new Rating("569", 5));
        expectedRating1.append(new Rating("575", 5));
        expectedRating1.append(new Rating("585", 5));
        expectedRating1.append(new Rating("638", 3));
        expectedRating1.append(new Rating("646", 5));
        expectedRating1.append(new Rating("654", 3));
        expectedRating1.append(new Rating("671", 4));

        LinkedListNode<Rating> expectedRating2 = new LinkedListNode<>(new Rating("17",3), null);
        expectedRating2.append(new Rating("23", 3));
        expectedRating2.append(new Rating("262", 2));

        Movie expected1 = addRatingsToMovie(expectedRating1, findMovie("Sicko", movieData));
        Movie expected2 = addRatingsToMovie(expectedRating2, findMovie("Ronin", movieData));

        ArrayList<Movie> expected = new ArrayList<>(Arrays.asList(expected2, expected1));

        assertTrue(compareMovies(computed, expected));
    }

    private boolean compareMovies(ArrayList<Movie> computed, ArrayList<Movie> expected) {
        if (computed == null || expected == null || computed.size() != expected.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            int locate = findIndex(expected.get(i).getTitle(), computed);
            if (locate == -1) {
                return false;
            } else {
                if (!compareRating(computed.get(locate).getRatings(), expected.get(i).getRatings())) {
                    return false;
                }
            }
        }
        return true;
    }

    private int findIndex(String find, ArrayList<Movie> in) {
        if (in == null) {
            return -1;
        }
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getTitle().equals(find)) {
                return i;
            }
        }
        return -1;
    }

    private Movie findMovie(String movieName, ArrayList<Movie> data) {
        if (data == null) {
            return null;
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getTitle().equals(movieName)) {
                return data.get(i);
            }
        }
        return null;
    }

    private Movie addRatingsToMovie(LinkedListNode<Rating> ratings, Movie movie) {
        LinkedListNode<Rating> rating = ratings;
        Movie retVal = movie;
        while (rating != null) {
            retVal.addRating(rating.getValue());
            rating = rating.getNext();
        }
        return retVal;
    }

    @Test
    public void testMediaLibrary() {
        MediaLibrary library = new MediaLibrary();

        library.populateLibrary("data/ratings.csv", "data/movies.csv", "movie_ratings.csv");
        ArrayList<Ratable> computed = library.topKRatables(10);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Ice So White");
        expected.add("20 mins");
        expected.add("Cupid - Twin Ver.");
        expected.add("la mama de la mama");
        expected.add("Fireflies");
        expected.add("Through The Wire");
        expected.add("Uptown Funk");
        expected.add("Passionfruit");
        expected.add("Bury the Light");
        expected.add("Never Gonna Give You Up");

        assertTrue(compareArrays(expected, computed));

        /*
        test edge cases: TopK_OnlyHandlesSongs
        where movies come on the top of the ranking
        */

        library = new MediaLibrary();

        library.populateLibrary("false.csv", "data/movies.csv", "data/movie_ratings.csv");
        computed = library.topKRatables(5);

        expected = new ArrayList<>();
        expected.add("Laura");
        expected.add("Sleepless in Seattle");
        expected.add("Galaxy Quest");
        expected.add("The Thomas Crown Affair");
        expected.add("While You Were Sleeping");

        assertTrue(compareArrays(expected, computed));
    }

    private boolean compareArrays(ArrayList<String> expected, ArrayList<Ratable> computed) {
        if (computed.size() != expected.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!computed.get(i).getTitle().equals(expected.get(i))) {
                return false;
            }
        }
        return true;
    }

}
