package ratings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public static ArrayList<Song> readSongs(String filename) {
        //return the following format "songID,artist,title,reviewerID,rating".
        try {
            ArrayList<String> file = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
            HashMap<String, Song> stored = new HashMap<>();
            for (String line: file) {
                ArrayList<String> splitLines = new ArrayList<>(Arrays.asList(line.split(",")));
                String songID = splitLines.get(0);
                String artist = splitLines.get(1);
                String title = splitLines.get(2);
                String reviewerID = splitLines.get(3);
                int rating = Integer.parseInt(splitLines.get(4));

                if (!stored.containsKey(songID)) {
                    stored.put(songID, new Song(title, artist, songID));
                }
                stored.get(songID).addRating(new Rating(reviewerID, rating));
            }
            ArrayList<Song> retVal = new ArrayList<>();
            for (Map.Entry<String, Song> keyVal: stored.entrySet()) {
                retVal.add(keyVal.getValue());
            }
            return retVal;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static ArrayList<Movie> readMovies(String filename) {
        //return the following format "movieTitle,castMember0,castMember1,castMember2,etc".
        try {
            ArrayList<String> file = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
            ArrayList<Movie> retVal = new ArrayList<>();
            for (String line: file) {
                ArrayList<String> splitLine = new ArrayList<>(Arrays.asList(line.split(",")));
                String movieTitle = splitLine.get(0);
                ArrayList<String> movieCasts = new ArrayList<>();
                for (int i = 1; i < splitLine.size(); i++) {
                    movieCasts.add(splitLine.get(i));
                }
                retVal.add(new Movie(movieTitle, movieCasts));
            }
            return retVal;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

}
