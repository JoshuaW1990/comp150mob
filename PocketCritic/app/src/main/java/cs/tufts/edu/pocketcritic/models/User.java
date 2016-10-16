package cs.tufts.edu.pocketcritic.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by junwang on 10/11/16.
 */
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
