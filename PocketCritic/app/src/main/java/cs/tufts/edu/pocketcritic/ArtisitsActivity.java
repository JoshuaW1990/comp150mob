package cs.tufts.edu.pocketcritic;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
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
import cs.tufts.edu.pocketcritic.support.RetrieveFromFirebase;

public class ArtisitsActivity extends AppCompatActivity implements View.OnClickListener {
    Button artistBio;
    String artistName;
    FirebaseDatabase database;
    String artistIntro;
    List<Album> albumList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artisits);


        Intent intent = getIntent();
        artistName = intent.getStringExtra("artistinfo");

        SetView(artistName);


        artistBio = (Button) findViewById(R.id.artist_Bio);
        artistBio.setOnClickListener(this);

        albumList = new ArrayList<Album>();

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
                new RetrieveFromFirebase(ArtisitsActivity.this).execute(albumList);
                /*List<Album> albums = new ArrayList<Album>();
                /for (DataSnapshot child: albumList.getChildren())
                {
                    String albumName = child.getKey();
                    long albumRating = (long) child.child("0").getValue();
                    String albumCoverURL = (String) child.child("1").getValue();
                    Album neoAlbum = new Album(albumName, albumRating, albumCoverURL);
                    albums.add(neoAlbum);
                    System.out.println(neoAlbum.albumName);
                    System.out.println(neoAlbum.albumCoverImageURL);
                }
                
                setAlbums(albums);

                saveAlbumList(albums);
                */

                //saveAlbumList(albums);
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


            for (int i = 0; i < albums.size(); i++)
            {
                // Add two albums view
                LinearLayout album1 = new LinearLayout(this);
                album1.setLayoutParams(param_album1);
                album1.setOrientation(LinearLayout.VERTICAL);

                Album album = albums.get(i);

                // Add album cover
                ImageView albumCover = new ImageView(this);
                new DownloadImageTask(albumCover).execute(album.albumCoverImageURL);
                album1.addView(albumCover);


                // Add album description: album name, album rating
                LinearLayout albumDescrip = new LinearLayout(this);
                albumDescrip.setOrientation(LinearLayout.VERTICAL);

                TextView albumName = new TextView(this);
                albumName.setText(album.albumName);
                albumName.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                TextView albumRating = new TextView(this);
                albumRating.setText("rating");
                albumRating.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                albumDescrip.addView(albumName);
                albumDescrip.addView(albumRating);
                album1.addView(albumDescrip);

                album2.addView(album1);
            }

            parent.addView(album2);
            // Add the more albums button

            System.out.println("number of albums:");
            System.out.println(albums.size());
            if (albums.size() > 2)
            {
                System.out.println("Add the button");
                Button moreAlbums = new Button(this);
                moreAlbums.setText("More Albums");
                moreAlbums.setId(R.id.artist_moreAlbums);
                LinearLayout mainParent = (LinearLayout) findViewById(R.id.artist_albumRoot);
                mainParent.addView(moreAlbums);
                moreAlbums.setOnClickListener(this);
            }

        }
    }


    private void setBandimage(String imageURL) {
        new DownloadImageTask((ImageView) findViewById(R.id.artist_BandImage)).execute(imageURL);
    }


    private void setBandname(String bandName) {
        TextView bandname = (TextView) findViewById(R.id.artist_artistName);
        bandname.setText(bandName);
    }


    public void saveAlbumList(List<Album> albums) {
        System.out.println("enter saveAlbumList function");
        System.out.println(albumList.size());
        if (albumList.size() > 0)
        {
            albumList.clear();
        }
        for (Album album: albums)
        {
            Album neoAlbum = new Album(album.albumName, album.albumRating, album.albumCoverImageURL);
            albumList.add(neoAlbum);
            System.out.println(neoAlbum.albumName);
            System.out.println(albumList.size());
        }
        System.out.println("Get all albums");
        System.out.println(albumList.get(0).albumName);
        System.out.println("exit saveAlbumList function");
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
        else if (id == R.id.artist_moreAlbums) {
            Intent intent = new Intent(ArtisitsActivity.this, AlbumlistActivity.class);
            intent.putExtra("artistName", artistName);
            startActivity(intent);
            finish();
        }

    }
}
