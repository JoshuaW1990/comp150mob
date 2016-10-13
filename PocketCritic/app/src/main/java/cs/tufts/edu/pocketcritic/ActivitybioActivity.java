package cs.tufts.edu.pocketcritic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

import cs.tufts.edu.pocketcritic.models.Artist;

public class ActivitybioActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private TextView textview;
    String[] strings = {null, null};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitybio);

        // Obtain the artist data
        Intent intent = getIntent();
        strings = intent.getStringArrayExtra("artistinfo");

        // back button
        back = (Button) findViewById(R.id.artistbio_back);
        back.setOnClickListener(this);

        // Set the text content: artist bio
        textview = (TextView) findViewById(R.id.artistbio_bio);
        textview.setText(strings[1]);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.artistbio_back) {
            Intent intent = new Intent(ActivitybioActivity.this, ArtisitsActivity.class);
            intent.putExtra("artistinfo", strings[0]);
            startActivity(intent);
            finish();
        }
    }
}
