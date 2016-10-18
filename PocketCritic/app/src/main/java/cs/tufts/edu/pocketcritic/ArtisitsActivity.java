package cs.tufts.edu.pocketcritic;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.content.Context;
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
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bandName = (String) dataSnapshot.getKey();
                String imageURL;
                String bio;
                imageURL = (String) dataSnapshot.child("0").getValue();
                bio = (String) dataSnapshot.child("2").getValue();
                Artist artist = new Artist(bandName, imageURL, bio);
                System.out.println(artist.bandName); // DEBUGGING
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
                    System.out.println(neoAlbum.albumName); // DEBUGGING
                    System.out.println(neoAlbum.albumCoverImageURL); // DEBUGGING
                }
                
                setAlbums(albums);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String message = "Server error. Refresh page";
                Toast.makeText(ArtisitsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setArtist(Artist artist) {
        setBandimage(artist.imageURL);
        setBandname(artist.bandName);
        artistIntro = artist.bio;
    }

    public void setAlbums(List<Album> albums) {
        if (albums.size() != 0) {

            System.out.println("Come into the setAlbums function");
            LinearLayout parent = (LinearLayout) findViewById(R.id.artist_albums);

            LinearLayout.LayoutParams param_album2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, 360);

            LinearLayout album2 = new LinearLayout(this);
            album2.setOrientation(LinearLayout.HORIZONTAL);
            album2.setLayoutParams(param_album2);
            LinearLayout.LayoutParams param_album1 = new LinearLayout.LayoutParams(240, ActionBar.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams param_albumDescrip = new LinearLayout.LayoutParams(240, 120);

            for (int i = 0; i < albums.size(); i++)
            {

                LinearLayout album1 = new LinearLayout(this);
                album1.setLayoutParams(param_album1);
                album1.setOrientation(LinearLayout.VERTICAL);
                ImageView albumCover = new ImageView(this);
                //albumCover.getLayoutParams().width = ActionBar.LayoutParams.MATCH_PARENT;
                //albumCover.getLayoutParams().height = ActionBar.LayoutParams.WRAP_CONTENT;
                //albumCover.setImageResource(R.drawable.album1);
                Album album = albums.get(i);
                new DownloadImageTask(albumCover).execute(album.albumCoverImageURL);
                album1.addView(albumCover);

                LinearLayout albumDescrip = new LinearLayout(this);
                //albumDescrip.setLayoutParams(param_albumDescrip);
                albumDescrip.setOrientation(LinearLayout.VERTICAL);
                TextView albumName = new TextView(this);
                albumName.setText(album.albumName);
                albumName.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                //albumName.setGravity();
                //albumName.getLayoutParams().height = 60;
                TextView albumRating = new TextView(this);
                albumRating.setText("rating");
                albumRating.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                albumDescrip.addView(albumName);
                albumDescrip.addView(albumRating);
                album1.addView(albumDescrip);

                album2.addView(album1);

            }

            parent.addView(album2);

            /*
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout parent = (LinearLayout) findViewById(R.id.artist_albums);
            View album1 = inflater.inflate(R.layout.artist_album1, null);
            TextView albumName = (TextView) album1.findViewById(R.id.artist_albumName);
            TextView albumRating = (TextView) album1.findViewById(R.id.artist_albumRating);
            albumName.setText("Let it be");
            albumRating.setText("Rating 3.9");
            Album album = albums.get(0);
            new DownloadImageTask((ImageView) findViewById(R.id.artist_albumImage)).execute(album.albumCoverImageURL);
            parent.addView(album1);

            setContentView(parent);
            */

        }
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
