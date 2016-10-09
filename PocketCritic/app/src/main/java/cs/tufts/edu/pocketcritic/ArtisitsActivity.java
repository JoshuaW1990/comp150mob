package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ArtisitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artisits);
        final Button BioButton = (Button) findViewById(R.id.BioButton);
        BioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchactivity= new Intent(ArtisitsActivity.this,ActivitybioActivity.class);
                startActivity(launchactivity);
            }
        });

    }

    public void more_albums(View view) {
        Intent launchactivity2 = new Intent(ArtisitsActivity.this, AlbumlistActivity.class);
        startActivity(launchactivity2);
    }
}
