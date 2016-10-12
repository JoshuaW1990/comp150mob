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

public class ArtisitsActivity extends AppCompatActivity {
    ImageView bandimage;
    TextView bandname;
    private Button artistBio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artisits);


        Intent intent = getIntent();
        String[] strings = intent.getStringArrayExtra("artistinfo");
        Artist artist = new Artist(strings[0], strings[1], strings[2]);

        setBandname(artist);
        System.out.println("Open the artist info image");
        setBandimage(artist); // Bug is here


        final Button BioButton = (Button) findViewById(R.id.BioButton);
        BioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchactivity= new Intent(ArtisitsActivity.this,ActivitybioActivity.class);
                startActivity(launchactivity);
            }
        });

    }


    private void setBandimage(Artist artist) {
        System.out.println("Start to set band image");
        new DownloadImageTask((ImageView) findViewById(R.id.artist_BandImage)).execute(artist.imageURL);
        System.out.println("finish to set band image");
    }


    private void setBandname(Artist artist) {
        bandname = (TextView) findViewById(R.id.artist_artistName);
        bandname.setText(artist.bandName);
    }

    public void more_albums(View view) {
        Intent launchactivity2 = new Intent(ArtisitsActivity.this, AlbumlistActivity.class);
        startActivity(launchactivity2);
    }
}
