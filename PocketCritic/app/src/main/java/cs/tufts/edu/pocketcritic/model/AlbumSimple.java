package cs.tufts.edu.pocketcritic.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junwang on 11/16/16.
 */

public class AlbumSimple {
    public String id;
    public String imageURL;
    public String name;
    public String artist;
    public double rate_num = 0;
    public double average_rate = 0.0;
    public Map<String, Double> stars = new HashMap<>();


    public AlbumSimple() {
        // Default constructor
    }

    public AlbumSimple(String id, String name, String imageURL, String artist) {
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
        this.artist = artist;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("imageURL", imageURL);
        result.put("name", name);
        result.put("artist", artist);
        result.put("rate_num", rate_num);
        result.put("average_rate", average_rate);
        result.put("stars", stars);

        return result;
    }

}
