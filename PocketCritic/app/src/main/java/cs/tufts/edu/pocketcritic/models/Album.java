package cs.tufts.edu.pocketcritic.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by junwang on 10/12/16.
 */

public class Album {
    String albumName;
    double albumRating;
    String albumCoverURL;

    public Album() {

    }

    public Album(String albumName, double albumRating, String albumCoverURL) {
        this.albumName = albumName;
        this.albumRating = albumRating;
        this.albumCoverURL = albumCoverURL;
    }

    public void CopyAlbum(Album newAlbum) {
        this.albumName = newAlbum.albumName;
        this.albumRating = newAlbum.albumRating;
        this.albumCoverURL = newAlbum.albumCoverURL;
    }

    public void searchAlbum(String artistName, String albumName) {
        this.albumName = albumName;
        System.out.println("start to search the albums");
        System.out.println("albumName");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("demoDatabase").child(artistName).child("3");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                albumRating = (double) dataSnapshot.child("0").getValue();
                albumCoverURL = (String) dataSnapshot.child("1").getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
