package ratings.datastructures;

import ratings.Song;

public class SongTitleComparator extends Comparator<Song>{

    public boolean compare(Song a, Song b) {
        String song1 = a.getTitle();
        String song2 = b.getTitle();
        int compare = song1.compareToIgnoreCase(song2);
        if (compare < 0) {
            return true;
        }
        return false;
    }

}
