package cs.tufts.edu.pocketcritic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.app.Activity;
import android.widget.TextView;

public class ActivitybioActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textview;
    private Button back;
    String[] artistinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitybio);

        Intent intent = getIntent();
        artistinfo = intent.getStringArrayExtra("artistinfo");

        textview = (TextView) findViewById(R.id.artistbio_intro);
        textview.setText(artistinfo[1]);

        back = (Button) findViewById(R.id.artistbio_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.artistbio_back) {
            Intent intent = new Intent(ActivitybioActivity.this, ArtisitsActivity.class);
            intent.putExtra("artistinfo", artistinfo[0]);
            startActivity(intent);
            finish();
        }

    }
}
