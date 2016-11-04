package cs.tufts.edu.pocketcritic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junwang on 11/3/16.
 */

public class ArtistSimple {
    public String imageURL;
    public String name;
    public int popularity;


    public ArtistSimple() {
        // Default constructor
    }

    public ArtistSimple(String name, String imageURL, int popularity) {
        this.imageURL = imageURL;
        this.name = name;
        this.popularity = popularity;
    }
}
