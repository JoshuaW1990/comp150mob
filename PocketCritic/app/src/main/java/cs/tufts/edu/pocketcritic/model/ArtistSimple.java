package cs.tufts.edu.pocketcritic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junwang on 11/3/16.
 */

public class ArtistSimple {
    public String bandName;
    public String imageURL;
    public List<String> genres;
    public String id;
    public int popularity;


    public ArtistSimple() {
        // Default constructor
    }

    public ArtistSimple(String bandName, String imageURL, List<String> genres, String id, int popularity) {
        this.bandName = bandName;
        this.imageURL = imageURL;
        this.id = id;
        this.popularity = popularity;
        this.genres = new ArrayList<String>();
        for (String genre: genres) {
            String neoGenre = new String(genre);
            this.genres.add(neoGenre);

        }
    }
}
