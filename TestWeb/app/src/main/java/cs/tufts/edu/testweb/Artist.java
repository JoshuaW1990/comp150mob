package cs.tufts.edu.testweb;

/**
 * Created by junwang on 11/11/16.
 */

public class Artist {

    /**
     * id : 6XyY86QOPPrYVGvF9ch6wz
     * image : https://i.scdn.co/image/e6a67fb4e286aae86a7ff7edb5e0fe142eda8aca
     * name : Linkin Park
     * popularity : 83
     */

    private String id;
    private String image;
    private String name;
    private int popularity;

    public Artist() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
