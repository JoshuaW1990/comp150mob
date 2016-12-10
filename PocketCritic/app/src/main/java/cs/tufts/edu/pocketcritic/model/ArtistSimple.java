package cs.tufts.edu.pocketcritic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junwang on 11/3/16.
 */

public class ArtistSimple {
    public String id;
    public String imageURL;
    public String name;
    public int popularity = 0;
    public List<String> genres = new ArrayList<>();
    public Map<String, Boolean> stars = new HashMap<>();


    public ArtistSimple() {
        // Default constructor
    }

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
    }
}
