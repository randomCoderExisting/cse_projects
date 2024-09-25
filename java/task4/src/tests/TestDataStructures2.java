package tests;

import ratings.Playlist;
import ratings.Rating;
import ratings.Song;

import org.junit.Test;
import ratings.datastructures.Comparator;
import ratings.datastructures.LinkedListNode;
import ratings.datastructures.SongBayesianRatingComparator;
import ratings.datastructures.SongTitleComparator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TestDataStructures2 {

    private boolean compareSong(LinkedListNode<Song> input, String title, String artist, ArrayList<Integer> expectedRating) {
        if (input == null) {
            return false;
        }
        if (!input.getValue().getTitle().equals(title)) {
            return false;
        }
        if (!input.getValue().getArtist().equals(artist)) {
            return false;
        }
        if (input.getValue().getRatings() != null) {
            return compareRating(input.getValue().getRatings(), expectedRating);
        }
        else {
            if (expectedRating.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean compareRating(LinkedListNode<Rating> input, ArrayList<Integer> expectedRating) {
        LinkedListNode<Rating> thisNode = input;
        int index = 0;
        while (thisNode != null) {
            if (thisNode.getValue().getRating() != expectedRating.get(index)) {
                return false;
            }
            index++;
            thisNode = thisNode.getNext();
        }
        return true;
    }

    @Test
    public void testPlaylist() {
        Song song01 = new Song("A random song", "A", "vabnZ9-ex7o"); //3.25
        song01.addRating(new Rating("Jesse", 3));
        song01.addRating(new Rating("Paul", 4));

        Song song02 = new Song("Cat", "B", "mbanP4-xd5f"); //3.75
        song02.addRating(new Rating("Jesse", 5));
        song02.addRating(new Rating("Paul", 4));

        Song song03 = new Song("Java", "C", "cpp5O4-fD6x"); //1.8
        song03.addRating(new Rating("Mark", 1));
        song03.addRating(new Rating("Names", 1));
        song03.addRating(new Rating("Jesse", 1));

        Song song04 = new Song("Zoo", "D", "py1h05-h131"); //4.2
        song04.addRating(new Rating("User01", 5));
        song04.addRating(new Rating("Mark", 5));
        song04.addRating(new Rating("Jesse", 5));

        Song song05 = new Song("Dog", "E", "usuckass-l69"); //3.0

        //create a comparison class based on extra ratings
        Comparator<Song> compareByRatings = new SongBayesianRatingComparator();

        //create a new Playlist that has songs in it
        Playlist playlist01 = new Playlist(compareByRatings);
        playlist01.addSong(song01);
        playlist01.addSong(song02);
        playlist01.addSong(song03);
        playlist01.addSong(song04);
        playlist01.addSong(song05);

        LinkedListNode<Song> computed = playlist01.getSongList();

        //Zoo -> Cat -> A random Song -> Dog -> Java

        assertTrue(compareSong(computed, "Zoo", "D", new ArrayList<>(Arrays.asList(5, 5, 5))));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "Cat", "B", new ArrayList<>(Arrays.asList(5, 4))));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "A random song", "A", new ArrayList<>(Arrays.asList(3, 4))));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "Dog", "E", new ArrayList<>()));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "Java", "C", new ArrayList<>(Arrays.asList(1, 1, 1))));

        //create a Playlist sorted by titles in alphabetical order

        Comparator<Song> compareByTitles = new SongTitleComparator();

        Playlist playlist02 = new Playlist(compareByTitles);
        playlist02.addSong(song01);
        playlist02.addSong(song02);
        playlist02.addSong(song03);
        playlist02.addSong(song04);
        playlist02.addSong(song05);

        computed = playlist02.getSongList();

        //A random Song -> Cat -> Dog -> Java -> Zoo

        assertTrue(compareSong(computed, "A random song", "A", new ArrayList<>(Arrays.asList(3, 4))));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "Cat", "B", new ArrayList<>(Arrays.asList(5, 4))));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "Dog", "E", new ArrayList<>()));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "Java", "C", new ArrayList<>(Arrays.asList(1, 1, 1))));

        computed = computed.getNext();
        assertTrue(compareSong(computed, "Zoo", "D", new ArrayList<>(Arrays.asList(5, 5, 5))));
    }

}
