package cs.tufts.edu.pocketcritic.models;

/**
 * Created by junwang on 10/12/16.
 */

public class Album {
    public String albumName;
    public long albumRating;
    public String albumCoverImageURL;

    public Album() {

    }

    public Album(String name, long rating, String url) {
        this.albumName = name;
        this.albumRating = rating;
        this.albumCoverImageURL = url;
    }
}
