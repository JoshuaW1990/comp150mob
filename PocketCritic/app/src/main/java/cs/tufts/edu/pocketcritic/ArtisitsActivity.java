package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import cs.tufts.edu.pocketcritic.models.Artist;
import cs.tufts.edu.pocketcritic.support.DownloadImageTask;

public class ArtisitsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView bandname;
    private Button artistbio;
    Artist artist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artisits);


        // Obtain the artist data
        Intent intent = getIntent();
        String artistName = intent.getStringExtra("artistinfo");
        System.out.println("artist info page");
        artist = new Artist();
        artist.searchArtist(artistName);
        System.out.println("back to artist info page");

        // Set the band name and the band image
        setBandname(artist);
        setBandimage(artist);

        // Set the artist bio button
        artistbio = (Button) findViewById(R.id.artists_bio);
        artistbio.setOnClickListener(this);


    }


    private void setBandimage(Artist artist) {
        new DownloadImageTask((ImageView) findViewById(R.id.artists_BandImage)).execute(artist.imageURL);
    }


    private void setBandname(Artist artist) {
        bandname = (TextView) findViewById(R.id.artists_artistName);
        bandname.setText(artist.bandName);
    }

    public void more_albums(View view) {
        Intent launchactivity2 = new Intent(ArtisitsActivity.this, AlbumlistActivity.class);
        startActivity(launchactivity2);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.artists_bio) {
            Intent intent = new Intent(ArtisitsActivity.this, ActivitybioActivity.class);
            String[] strings = {artist.bandName, artist.bio};
            intent.putExtra("artistinfo", strings);
            startActivity(intent);
        }
    }
}
