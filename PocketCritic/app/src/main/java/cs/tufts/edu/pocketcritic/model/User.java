package cs.tufts.edu.pocketcritic.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junwang on 10/11/16.
 */
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    /* the idea is to have a Hashmap of the artists this user has rated. Has an artistName as the
     * key for the artist that has been written, and a boolean value for a like or a dilike
     */

    public Map<String, String> liked_artists;
    public Map<String, String> disliked_artists;


    // use album ID
    public Map<String, Long> rated_albums;

    public User() {


    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.liked_artists = new HashMap<String, String>();
        this.disliked_artists = new HashMap<String, String>();
        this.rated_albums = new HashMap<String, Long>();

        //test
//        this.liked_artists.put("ArtistID", "The Beatles");
//        this.disliked_artists.put("ArtistID", "Justin Bieber");
    }
}

