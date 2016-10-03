package cs.tufts.edu.pocketcritic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.app.Activity;

public class ActivitybioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitybio);

        final Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchactivity= new Intent(ActivitybioActivity.this,MainActivity.class);
                startActivity(launchactivity);
            }
        });
    }
}
