package cs.tufts.edu.pocketcritic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;

import cs.tufts.edu.pocketcritic.models.Artist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavigationActivity extends AppCompatActivity {

    // Initialization
    private SearchView searchview;
    private TextView textview;

    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);

        searchview = (SearchView) findViewById(R.id.search);
        searchview.setQueryHint("Artist Name");

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        textview = (TextView) findViewById(R.id.userid);
        textview.setText(username);

        // Set listener
        searchview.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchArtist(query);
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchArtist(newText);
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void SearchArtist(String query) {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("demoDatabase").child(query);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bandName = (String) dataSnapshot.getKey();
                String imageURL;
                String bio;
                imageURL = (String) dataSnapshot.child("0").getValue();
                bio = (String) dataSnapshot.child("2").getValue();
                Artist artist = new Artist(bandName, imageURL, bio);
                onSearchSuccess(artist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void onSearchSuccess(Artist artist) {
        Intent intent = new Intent(this, ArtisitsActivity.class);
        String[] strings = new String[] {artist.bandName, artist.imageURL, artist.bio};
        intent.putExtra("artistinfo", strings);
        startActivity(intent);
        finish();
    }




}
