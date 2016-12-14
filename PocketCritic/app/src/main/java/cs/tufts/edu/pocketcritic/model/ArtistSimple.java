package cs.tufts.edu.pocketcritic.model;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> newSpotify

/**
 * Created by junwang on 11/3/16.
 */

public class ArtistSimple {
<<<<<<< HEAD
    public String imageURL;
    public String name;
    public int popularity;
=======
    public String id;
    public String imageURL;
    public String name;
    public int popularity = 0;
    public Map<String, Object> genres = new HashMap<>();
    public Map<String, Boolean> stars = new HashMap<>();
>>>>>>> newSpotify


    public ArtistSimple() {
        // Default constructor
    }

<<<<<<< HEAD
    public ArtistSimple(String name, String imageURL, int popularity) {
        this.imageURL = imageURL;
        this.name = name;
        this.popularity = popularity;
=======
    public ArtistSimple(String id, String name, String imageURL) {
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("imageURL", imageURL);
        result.put("name", name);
        result.put("popularity", popularity);
        result.put("genres", genres);
        result.put("stars", stars);

        return result;
>>>>>>> newSpotify
    }
}
