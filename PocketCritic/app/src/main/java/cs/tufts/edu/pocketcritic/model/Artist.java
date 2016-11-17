package cs.tufts.edu.pocketcritic.model;

/**
 * Created by junwang on 11/10/16.
 */

public class Artist {

    /**
     * image : https://i.scdn.co/image/452dda43bb548452614feb72a87ad93fb6515f7a
     * popularity : 62
     * name : Within Temptation
     * id : 3hE8S8ohRErocpkY7uJW4a
     */

    private String image;
    private int popularity;
    private String name;
    private String id;

    public Artist() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
