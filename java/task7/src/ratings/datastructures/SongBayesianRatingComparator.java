package ratings.datastructures;

import ratings.Song;

public class SongBayesianRatingComparator extends Comparator<Song> {

    public boolean compare(Song a, Song b) {
        double rate01 = a.bayesianAverageRating(2, 3);
        double rate02 = b.bayesianAverageRating(2,3);
        if (rate01 > rate02) {
            return true;
        }
        return false;
    }

}