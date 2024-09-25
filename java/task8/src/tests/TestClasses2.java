package tests;

import org.junit.Test;
import ratings.Movie;
import ratings.Rating;
import ratings.Song;
import ratings.datastructures.SongBayesianRatingComparator;
import ratings.datastructures.SongTitleComparator;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TestClasses2 {

    private static boolean compareDoubles(double a, double b) {
        return Math.abs(a - b) < 0.01;
    }

    @Test
    public void testSongTitleComparator() {
        //return true if the first song title comes before the second song alphabetically, return false otherwise
        Song song01 = new Song("Come As You Are", "Nirvana", "vabnZ9-ex7o");
        Song song02 = new Song("What are you testing", "Art", "mbanP4-xd5f");

        //no lower/upper case title are considered
        SongTitleComparator compareSongs = new SongTitleComparator();

        boolean computed = compareSongs.compare(song01, song02);
        assertTrue(song01.getTitle() + " comes before " + song02.getTitle(), computed); //song01 should come before song02

        //"aa" comes before "aaa"
        song01 = new Song("aa", "Artist1", "random-id01");
        song02 = new Song("aaa", "Artist2", "random-id23");

        computed = compareSongs.compare(song01, song02);
        assertTrue(computed);

        //empty String comes before any other String
        song01 = new Song("", "IDK", "1d-isR4nd0m01");
        song02 = new Song("Any other Song", "RandomPerson", "id1s-n0t04");

        computed = compareSongs.compare(song01, song02);
        assertTrue(computed);

        //comparing the same exact song
        song01 = new Song("Hello", "IDK", "1d-isR4nd0m01");
        song02 = new Song("Hello", "IDK", "1d-isR4nd0m01");

        computed = !compareSongs.compare(song02, song01);
        assertTrue(computed);

        //song titles should not be case-sensitive
        song01 = new Song("a", "grapes", "ra50m-d8c4");
        song02 = new Song("B", "GRAPES", "RA50M-D8C4");

        computed = !compareSongs.compare(song02, song01);
        assertTrue(computed);
    }

    @Test
    public void testSongBayesianRatingComparator() {
        //test the method in which compares the SongBayesianRating of two songs using 2 extra Ratings of 3.
        Song song01 = new Song("Come As You Are", "Nirvana", "vabnZ9-ex7o");
        song01.addRating(new Rating("Names", 4));
        song01.addRating(new Rating("Mark", 5));

        Song song02 = new Song("What are you testing", "Art", "mbanP4-xd5f");
        song02.addRating(new Rating("Names", 1));
        song02.addRating(new Rating("Mark", 2));

        SongBayesianRatingComparator compareBayesianRating = new SongBayesianRatingComparator();

        boolean computed = compareBayesianRating.compare(song01, song02);
        assertTrue(computed);

        //test songs with no ratings

        Song song04 = new Song("Come As You Are", "Nirvana", "vabnZ9-ex7o");
        Song song05 = new Song("Come As You Are", "Nirvana", "vabnZ9-ex7o");

        computed = compareBayesianRating.compare(song04, song05);
        assertTrue(!computed);
    }

    /**
     * Non-passed Auto Lab Section
     **/

    @Test
    public void testBayesianAverageRating() {
        //extraBayesianRating
        Song song = new Song("A Song", "Anonymous Artist", "id01");
        song.addRating(new Rating("Jesse", 4));
        song.addRating(new Rating("Mark", 5));

        double computed = song.bayesianAverageRating(2, 3);
        assertTrue(compareDoubles(computed, 3.75));

        //bayesianBadOnZeroRatings (aka song has 0 rating & 0 additional ratings)
        song = new Song("A Song", "Anonymous Artist", "id01");

        computed = song.bayesianAverageRating(0, 4);
        assertTrue(compareDoubles(computed, 0.0));
    }

    @Test
    public void testMovieClass() {
        //Create a new Movie class to test its functionality
        Movie movie = new Movie("Movie Name Here", new ArrayList<>(Arrays.asList("Chris Pratt", "Zoe Saldana", "Dave Bautista")));
        assertTrue(movie.getTitle().equals("Movie Name Here"));
        assertTrue(compareArrayList(movie.getCast(), new ArrayList<>(Arrays.asList("Chris Pratt", "Zoe Saldana", "Dave Bautista"))));
        assertTrue(compareArrayList(movie.getCast(), new ArrayList<>(Arrays.asList("chris pratt", "zoe saldana", "dave bautista"))));
        assertTrue(compareArrayList(movie.getCast(), new ArrayList<>(Arrays.asList("CHRIS pratt", "ZoE SalDANA", "dAVE bautistA"))));
    }

    @Test
    public void testMovieBayesianRating() {
        Movie movie = new Movie("Movie One", new ArrayList<>(Arrays.asList("Chris Pratt", "Zoe Saldana", "Dave Bautista")));
        movie.addRating(new Rating("Jesse", 4));
        movie.addRating(new Rating("Mark", 5));

        double computed = movie.bayesianAverageRating(2, 3);
        assertTrue(compareDoubles(computed, 3.75));

        //bayesianBadOnZeroRatings (aka song has 0 rating & 0 additional ratings)
        movie = new Movie("Movie Two", new ArrayList<>(Arrays.asList("Chris Pratt", "Zoe Saldana", "Dave Bautista")));

        computed = movie.bayesianAverageRating(0, 4);
        assertTrue(compareDoubles(computed, 0.0));
    }

    private boolean compareArrayList(ArrayList<String> input, ArrayList<String> input2) {
        if (input.size() == input2.size()) {
            for (int i = 0; i < input.size(); i++) {
                if (!input.get(i).equalsIgnoreCase(input2.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}