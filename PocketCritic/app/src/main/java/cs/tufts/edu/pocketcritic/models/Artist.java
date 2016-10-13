package cs.tufts.edu.pocketcritic.models;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

import cs.tufts.edu.pocketcritic.models.Album;

/**
 * Created by junwang on 10/12/16.
 */

public class Artist {
    public String bandName;
    public String imageURL;
    public String[] genres;
    public String bio;
    private String[] albumNames;
    public Album[] albums;
    FirebaseDatabase database;

    public Artist() {
        // Default constructor
    }

    public Artist(String bandName, String imageURL, String[] genres, String bio, Album[] albums) {
        setArtistName(bandName);
        setGenres(genres);
        setImageURL(imageURL);
        setBio(bio);
        setAlbums(albums);
    }

    public void searchArtist(String artistName) {
        System.out.println("start to search the artist");
        database = FirebaseDatabase.getInstance();
        System.out.println(artistName);
        DatabaseReference ref = database.getReference("demoDatabase").child(artistName);
        System.out.println(artistName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("come into onDataChange");
                String bandName = dataSnapshot.getKey();
                setArtistName(bandName);
                String imageURL = (String) dataSnapshot.child("0").getValue();
                setImageURL(imageURL);
                String[] genres = (String[]) dataSnapshot.child("1").getValue();
                setGenres(genres);
                String bio = (String) dataSnapshot.child("2").getValue();
                setBio(bio);
                String[] albumNames = (String[]) dataSnapshot.child("3").getValue();
                setAlbumNames(albumNames);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        if (this.albumNames == null) {
            setAlbums(null);
        }
        else
        {
            int length = albumNames.length;
            Album[] neoAlbums = new Album[length];
            for(int i = 0; i < length; i++) {
                String name = albumNames[i];
                neoAlbums[i].searchAlbum(artistName, name);
            }
            setAlbums(neoAlbums);
        }
    }

    private void setArtistName(String name) {
        this.bandName = name;
    }

    private void setImageURL(String url) {
        this.imageURL = url;
    }

    private void setGenres(String[] genres) {
        this.genres = genres;
    }

    private void setBio(String bio) {
        this.bio = bio;
    }

    private void setAlbums(Album[] albums) {
        this.albums = albums;
    }

    private void setAlbumNames(String[] names) {
        this.albumNames = names;
    }

}
