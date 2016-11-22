package cs.tufts.edu.pocketcritic.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junwang on 11/22/16.
 */

public class Genre {

    public int popularity;
    public Map<String, Boolean> userRecord = new HashMap<>();

    public Genre() {}

    public Genre(int popularity) {
        this.popularity = popularity;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("popularity", popularity);
        result.put("userRecrod", userRecord);

        return result;
    }

}
