package cs.tufts.edu.pocketcritic.model;

/**
 * Created by junwang on 11/11/16.
 */

public class Album {

    /**
     * artist : Within Temptation
     * id : 1ikFpLvXp88Y1550QvYVIZ
     * image : https://i.scdn.co/image/2ea4da742007b1ef1c89cc0be7a78a5e466e2580
     * name : The Unforgiving (Special Edition)
     */

    private String artist;
    private String id;
    private String image;
    private String name;

    public Album() {}

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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
}
