package ratings;

import java.util.ArrayList;

public class MediaLibrary {

    private ArrayList<Song> songList;
    private ArrayList<Movie> movieList;

    public MediaLibrary() {}

    /*
    takes 3 Strings as parameters representing filenames for songs with ratings,
    movies (title and cast), then movie ratings in this order.
     */
    public void populateLibrary(String SongFilename, String movieFilename, String movieRatingFilename) {
        songList = FileReader.readSongs(SongFilename);
        movieList = FileReader.readMovieRatings(FileReader.readMovies(movieFilename), movieRatingFilename);
    }

    /*
    k is the input int (eg. topKRatables(10) returns a top 10 list. The ratables
    will be ranked by their bayesian average rating with 2 extra ratings of value 3,
    then return an ArrayList of Ratable
    */
    public ArrayList<Ratable> topKRatables(int k) {
        ArrayList<Ratable> data = new ArrayList<>();
        ArrayList<Ratable> sorted = new ArrayList<>();
        ArrayList<Ratable> retVal = new ArrayList<>();
        for (int i = 0; i < songList.size(); i++) {
            data.add(songList.get(i));
        }
        for (int i = 0; i < movieList.size(); i++) {
            data.add(movieList.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            sorted = insertRatable(data.get(i), sorted);
        }
        for (int i = 0; i < k; i++) {
            retVal.add(sorted.get(i));
        }
        return retVal;
    }

    /*
    [1, 3, 2, 4, 5] -> [5, 4, 3, 2, 1]
    [5, 4, 3, 2, 1] -> [5, 4, 3, 2, 1]
    [6, 7, 10, 1, 0] -> [10, 7, 6, 1, 0]

    this method will repeat until the current number is larger than the found number,
    then insert it at the index of the found number. Also cares about bayesianRating
    when comparing.

    current: the rating in the current index in data ArrayList
    input: the current sorted output ArrayList
     */
    private ArrayList<Ratable> insertRatable(Ratable current, ArrayList<Ratable> input) {
        ArrayList<Ratable> retVal = input;
        for (int i = 0; i < input.size(); i++) {
            if (current.bayesianAverageRating(2, 3) > input.get(i).bayesianAverageRating(2, 3)) {
                //insert at i
                retVal.add(i, current);
                return retVal;
            }
        }
        //else, add it at the back of the list
        retVal.add(current);
        return retVal;
    }

}
