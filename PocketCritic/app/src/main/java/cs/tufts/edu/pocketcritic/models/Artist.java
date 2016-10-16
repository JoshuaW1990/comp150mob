package cs.tufts.edu.pocketcritic.models;

import java.util.*;

import cs.tufts.edu.pocketcritic.models.Album;
/**
 * Created by junwang on 10/12/16.
 */

public class Artist {
    public String bandName;
    public String imageURL;
    public String bio;

    public Artist() {
        // Default constructor
    }

    public Artist(String bandName, String imageURL, String bio) {
        this.bandName = bandName;
        this.imageURL = imageURL;
        this.bio = bio;
    }
}
