package tests;

import org.junit.Test;
import ratings.FileReader;
import ratings.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class TestDataStructures3 {

    /*
    Test a static method named "readMovies"

    each line has the format of "movieTitle,castMember0,castMember1,castMember2,etc".
    must at least contain 1 cast member.

    return an ArrayList of Movies containing all information

    return empty ArrayList if the input filename DNE.

    The array will be in any order, the test must accept all orders with same content.

    The ratings of each song must be in order of when they first appeared in file.
    */
    @Test
    public void testReadMovies() {

        //return empty ArrayList if the input filename DNE.
        ArrayList<Movie> computed = FileReader.readMovies("data/invalid.csv");
        assertTrue(computed.isEmpty());

        computed = FileReader.readMovies("data/movies.csv");

        Movie movie = new Movie(
                "Toy Story", new ArrayList<>(Arrays.asList(
                        "Tom Hanks", "Tim Allen", "Don Rickles", "Wallace Shawn", "John Ratzenberger", "Annie Potts", "John Morris", "Laurie Metcalf", "R. Lee Ermey" , "Penn Jillette"
        )));

        assertTrue(compareMovie(computed, movie));
    }

    private boolean compareMovie(ArrayList<Movie> computed, Movie expected) {
        for (int i = 0; i < computed.size(); i++) {
            if (computed.get(i).getTitle().equals(expected.getTitle())) {
                return compareCast(computed.get(i).getCast(), expected.getCast());
            }
        }
        return false;
    }

    private boolean compareCast(ArrayList<String> computedCast, ArrayList<String> expectedCast) {
        if (computedCast.size() != expectedCast.size()) {
            return false;
        }
        for (int i = 0; i < computedCast.size(); i++) {
            if (!computedCast.get(i).equals(expectedCast.get(i))) {
                return false;
            }
        }
        return true;
    }

}
