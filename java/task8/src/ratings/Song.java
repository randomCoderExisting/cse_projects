package ratings;

import ratings.datastructures.LinkedListNode;
import ratings.Ratable;

public class Song extends Ratable {

    /*
This class will have a constructor that takes 3 parameters in this order:
A String representing the title of the Song
A String representing the artist of the Song
A String representing the Songs ID
*/
    private String artist;
    private String song_id;

    public Song(String title, String artist, String song_id) {
        super.setTitle(title);
        setArtist(artist);
        setSongID(song_id);
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongID() {
        return this.song_id;
    }

    public void setSongID(String song_id) {
        this.song_id = song_id;
    }

}
