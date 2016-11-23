package cs.tufts.edu.pocketcritic.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junwang on 11/22/16.
 */

public class Genre {

    public String genreName;
    public int popularity;
    public Map<String, Integer> artists = new HashMap<>();

    public Genre() {}

    public Genre(String genreName, int popularity) {
        this.genreName = genreName;
        this.popularity = popularity;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("popularity", popularity);
        result.put("artists", artists);
        return result;
    }

}
