package tests;

import org.junit.Test;
import ratings.FileReader;
import ratings.Movie;
import ratings.DegreesOfSeparation;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Test
    public void testDegreesOfSeparation() {

        //DegreesOfSeparation has a constructor that takes an ArrayList of Movies
        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Movie1", new ArrayList<>(Arrays.asList("A", "B", "C"))));
        movieList.add(new Movie("Movie2", new ArrayList<>(Arrays.asList("A", "D", "E"))));
        movieList.add(new Movie("Movie3", new ArrayList<>(Arrays.asList("C", "F"))));
        movieList.add(new Movie("Movie4", new ArrayList<>(Arrays.asList("C", "G"))));
        movieList.add(new Movie("Movie5", new ArrayList<>(Arrays.asList("D", "G"))));
        movieList.add(new Movie("Movie6", new ArrayList<>(Arrays.asList("G", "H"))));
        movieList.add(new Movie("Movie7", new ArrayList<>(Arrays.asList("G", "I"))));
        movieList.add(new Movie("Movie8", new ArrayList<>(Arrays.asList("E", "I"))));

        DegreesOfSeparation degreesOfSeparation = new DegreesOfSeparation(movieList);

        //calculate the distance between movies
        assertTrue(degreesOfSeparation.degreesOfSeparation("A", "I") == 2);
        assertTrue(degreesOfSeparation.degreesOfSeparation("B", "I") == 3);
        assertTrue(degreesOfSeparation.degreesOfSeparation("H", "A") == 3);

        //if one of the given parameter is invalid
        assertTrue(degreesOfSeparation.degreesOfSeparation("Y", "Z") == -1);

        //the degree of separation between the actor itself
        assertTrue(degreesOfSeparation.degreesOfSeparation("G", "G") == 0);

        //if the two actors have acted in a movie together
        assertTrue(degreesOfSeparation.degreesOfSeparation("C", "F") == 1);
    }

}