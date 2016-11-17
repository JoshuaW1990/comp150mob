package cs.tufts.edu.pocketcritic.model;

/**
 * Created by junwang on 11/16/16.
 */

public class AlbumSimple {
    public String imageURL;
    public String name;

    public AlbumSimple() {
        // Default constructor
    }

    public AlbumSimple(String name, String imageURL) {
        this.imageURL = imageURL;
        this.name = name;
    }

}
