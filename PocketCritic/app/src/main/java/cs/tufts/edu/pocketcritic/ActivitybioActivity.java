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
    String artistintro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitybio);

        Intent intent = getIntent();
        artistintro = intent.getStringExtra("artistinfo");

        textview = (TextView) findViewById(R.id.artistbio_intro);
        textview.setText(artistintro);

        back = (Button) findViewById(R.id.artistbio_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.artistbio_back) {
            finish();
        }

    }
}
