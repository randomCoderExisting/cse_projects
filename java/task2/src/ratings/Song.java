package ratings;

public class Song {

    /*
This class will have a constructor that takes 3 parameters in this order:
A String representing the title of the Song
A String representing the artist of the Song
A String representing the Songs ID
*/
    private String title;
    private String artist;
    private String song_id;

    public Song(String title, String artist, String song_id) {
        this.title = title;
        this.artist = artist;
        this.song_id = song_id;
    }

    /*
Implement getter and setter methods for the three constructor parameters named:
1. getTitle
2. setTitle
3. getArtist
4. setArtist
5. getSongID <-- Note that both characters in ID are capital
6. setSongID
    */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
