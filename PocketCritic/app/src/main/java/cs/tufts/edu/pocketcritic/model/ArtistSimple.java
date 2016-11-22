package cs.tufts.edu.pocketcritic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by junwang on 11/3/16.
 */

public class ArtistSimple {
    public String imageURL;
    public String name;
    public int popularity;
    public Map<String, Object> genres = new HashMap<>();


    public ArtistSimple() {
        // Default constructor
    }

    public ArtistSimple(String name, String imageURL, int popularity) {
        this.imageURL = imageURL;
        this.name = name;
        this.popularity = popularity;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imageURL", imageURL);
        result.put("name", name);
        result.put("popularity", popularity);
        result.put("genres", genres);

        return result;
    }
}
