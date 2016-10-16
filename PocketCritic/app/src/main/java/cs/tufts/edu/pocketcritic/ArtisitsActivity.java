package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cs.tufts.edu.pocketcritic.models.Artist;
import cs.tufts.edu.pocketcritic.models.Album;
import cs.tufts.edu.pocketcritic.support.DownloadImageTask;

public class ArtisitsActivity extends AppCompatActivity implements View.OnClickListener {
    Button artistBio;
    String artistName;
    FirebaseDatabase database;
    String artistIntro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artisits);


        Intent intent = getIntent();
        artistName = intent.getStringExtra("artistinfo");

        SetView(artistName);


        artistBio = (Button) findViewById(R.id.artist_Bio);
        artistBio.setOnClickListener(this);

        //back = (Button) findViewById(R.id.artist_back);
        //back.setOnClickListener(this);
    }

    private void SetView(String artistName) {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("demoDatabase").child(artistName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bandName = (String) dataSnapshot.getKey();
                String imageURL;
                String bio;
                imageURL = (String) dataSnapshot.child("0").getValue();
                bio = (String) dataSnapshot.child("2").getValue();
                Artist artist = new Artist(bandName, imageURL, bio);
                System.out.println(artist.bandName);
                setArtist(artist);
                DataSnapshot albumList = dataSnapshot.child("3");
                List<Album> albums = new ArrayList<Album>();
                for (DataSnapshot child: albumList.getChildren())
                {
                    String albumName = child.getKey();
                    long albumRating = (long) child.child("0").getValue();
                    String albumCoverURL = (String) child.child("1").getValue();
                    Album neoAlbum = new Album(albumName, albumRating, albumCoverURL);
                    albums.add(neoAlbum);
                    System.out.println(neoAlbum.albumName);
                    System.out.println(neoAlbum.albumCoverImageURL);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void setArtist(Artist artist) {
        setBandimage(artist.imageURL);
        setBandname(artist.bandName);
        artistIntro = artist.bio;
    }


    private void setBandimage(String imageURL) {
        new DownloadImageTask((ImageView) findViewById(R.id.artist_BandImage)).execute(imageURL);
    }


    private void setBandname(String bandName) {
        TextView bandname = (TextView) findViewById(R.id.artist_artistName);
        bandname.setText(bandName);
    }

    public void more_albums(View view) {
        Intent launchactivity2 = new Intent(ArtisitsActivity.this, AlbumlistActivity.class);
        startActivity(launchactivity2);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.artist_Bio) {
            Intent intent = new Intent(ArtisitsActivity.this, ActivitybioActivity.class);
            String[] strings = {artistName, artistIntro};
            intent.putExtra("artistinfo", strings);
            startActivity(intent);
            finish();
        }
        /*
        else if (id == R.id.artist_back) {
            Intent intent = new Intent(ArtisitsActivity.this, NavigateActivity.class);
            startActivity(intent);
            finish();
        }
        */
    }
}
