package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs.tufts.edu.pocketcritic.models.Artist;
import cs.tufts.edu.pocketcritic.models.Album;

public class NavigateActivity extends AppCompatActivity
    implements View.OnClickListener {

    private EditText searchInfo;
    private Button searchButton;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigate);

        // user id
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        setUserID(username);

        // edit views
        searchInfo = (EditText) findViewById(R.id.navigate_searchinfo);

        // click listeners
        searchButton = (Button) findViewById(R.id.navigate_searchButton);
        searchButton.setOnClickListener(this);

    }

    public void doSearch() {
        String query = searchInfo.getText().toString();
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("demoDatabase").child(query);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    onSearchSuccess();
                else
                    Toast.makeText(NavigateActivity.this, R.string.search_failed,
                            Toast.LENGTH_SHORT).show();
                /*
                String bandName = (String) dataSnapshot.getKey();
                String imageURL;
                String bio;
                imageURL = (String) dataSnapshot.child("0").getValue();
                bio = (String) dataSnapshot.child("2").getValue();
                Artist artist = new Artist(bandName, imageURL, bio);
                */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    private void setUserID(String username) {
        TextView textview = (TextView) findViewById(R.id.navigate_userid);
        textview.setText(username);
    }

    public void searchAlbums(Artist artist) {
        System.out.println("Search albums");
        System.out.println(artist.bandName);
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("demoDatabase").child(artist.bandName).child("3");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("flag");
                List<Album> albums = new ArrayList<Album>();
                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String albumName = child.getKey();
                    long albumRating = (long) child.child("0").getValue();
                    String albumURL = (String) child.child("1").getValue();
                    Album album = new Album(albumName, albumRating, albumURL);
                    albums.add(album);
                    System.out.println(album.albumName);
                    System.out.print(album.albumRating);
                    System.out.println(album.albumCoverImageURL);
                }
                if (albums.size() == 0) {
                    System.out.println("Not found");
                }
                else
                {
                    //onSearchSuccess(artist, );
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void onSearchSuccess() {
        Intent intent = new Intent(this, ArtisitsActivity.class);
        //String[] strings = new String[] {artist.bandName, artist.imageURL, artist.bio};

        String query = searchInfo.getText().toString();
        intent.putExtra("artistinfo", query);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.navigate_searchButton) {
            doSearch();
        }
        else
        {
            System.out.println("error when searching!");
        }
    }
}
