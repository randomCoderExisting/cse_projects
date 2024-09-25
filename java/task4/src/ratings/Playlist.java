package ratings;

import ratings.datastructures.Comparator;
import ratings.datastructures.LinkedListNode;
import ratings.datastructures.SongTitleComparator;

import java.util.ArrayList;

public class Playlist {
    private Comparator<Song> compare;
    private ArrayList<Song> songList;

    public Playlist(Comparator<Song> compare) {
        this.compare = compare;
    }

    public void addSong(Song song) {
        if (songList == null) {
            songList = new ArrayList<>();
        }
        this.songList.add(song);
    }

    public LinkedListNode<Song> getSongList() {
        ArrayList<Song> sortedList = sort(this.songList);
        LinkedListNode<Song> retVal = null;
        for (int i = sortedList.size(); i > 0; i--) {
            retVal = new LinkedListNode<>(sortedList.get(i - 1), retVal);
        }
        return retVal;
    }

    public ArrayList<Song> sort(ArrayList<Song> input) {
        ArrayList<Song> retVal = new ArrayList<>();
        for (Song valueToInsert : input) {
            int location = 0;
            for (Song valueToCompare : retVal) {
                if (this.compare.compare(valueToCompare, valueToInsert)) {
                    location++;
                }
            }
            retVal.add(location, valueToInsert);
        }
        return retVal;
    }

}
