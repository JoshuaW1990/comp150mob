package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs.tufts.edu.pocketcritic.model.AlbumSimple;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.model.SingleAlbum;
import cs.tufts.edu.pocketcritic.model.SingleArtist;
import cs.tufts.edu.pocketcritic.support.SpotifyAlbumApi;
import cs.tufts.edu.pocketcritic.support.SpotifyAlbumInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumScrollingActivity extends AppCompatActivity {


    private String searchId;
    private FirebaseDatabase database;
    private SpotifyAlbumApi spotifyAlbumApi;
    private SpotifyAlbumInterface spotifyAlbumInterface;
    private Spinner rateSpinner;
    private String userID;
    private static final String TAG = "ArtistInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        searchId = intent.getStringExtra("searchId");

        System.out.println("come to artist page");
        System.out.println(searchId);

        spotifyAlbumApi = SpotifyAlbumApi.getApi();
        spotifyAlbumInterface = spotifyAlbumApi.getService();

        userID = getUid();

        rateSpinner = (Spinner) findViewById(R.id.album_rating_spinner);



        searchDatabaseById();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private String generateQueryString(String str) {
        return str.replaceAll("\\s+","+");
    }


    private void searchDatabaseById() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("albums").child(searchId);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    AlbumSimple album = dataSnapshot.getValue(AlbumSimple.class);
                    initView(album);
                }
                else {
                    searchSpotifyById();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }

    private void searchSpotifyById() {
        Call<SingleAlbum> call = spotifyAlbumInterface.getResult(searchId);
        call.enqueue(new Callback<SingleAlbum>() {
            @Override
            public void onResponse(Call<SingleAlbum> call, Response<SingleAlbum> response) {
                if (response.isSuccessful()) {
                    SingleAlbum result = response.body();
                    if (result != null) {
                        writeDatatoDatabase(result);
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleAlbum> call, Throwable t) {

            }
        });
    }

    private void writeDatatoDatabase(SingleAlbum album) {
        // Get the data to form the class of ArtistSimple
        String id = album.getId();
        String name = album.getName();
        String imageURL;
        if (album.getImages().size() > 0) {
            imageURL = album.getImages().get(0).getUrl();
        } else {
            imageURL = "None";
        }
        String artist = album.getArtists().get(0).getName();
        AlbumSimple albumSimple = new AlbumSimple(id, name, imageURL, artist);

        // Write the data to the artists database
        Map<String, Object> albumValue = albumSimple.toMap();

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/albums/" + searchId, albumValue);

        DatabaseReference myRef = database.getReference();
        myRef.updateChildren(childUpdates);
        initView(albumSimple);
    }

    private void initView(AlbumSimple album) {
        TextView textView = (TextView) findViewById(R.id.album_page_name);
        textView.setText(album.name);
        ImageView imgView = (ImageView) findViewById(R.id.album_page_img);
        Picasso.with(this).load(album.imageURL).into(imgView);

        List<String> list = new ArrayList<>();
        list.add("Awful");
        list.add("Poor");
        list.add("Good");
        list.add("Excellent");
        list.add("Classic");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        rateSpinner.setAdapter(dataAdapter);

        // Spinner item selection Listener
        addListenerOnSpinnerItemSelection();

        // Button click Listener
        addListenerOnButton();

    }

    // Add spinner data

    public void addListenerOnSpinnerItemSelection(){

        rateSpinner.setOnItemSelectedListener(new AlbumRateOnItemSelectedListener());
    }

    //get the selected dropdown list value

    public void addListenerOnButton() {

        rateSpinner = (Spinner) findViewById(R.id.album_rating_spinner);

        Button btnSubmit = (Button) findViewById(R.id.album_submit_rate);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(AlbumScrollingActivity.this,
                        "On Button Click : " +
                                "\n" + String.valueOf(rateSpinner.getSelectedItem()) ,
                        Toast.LENGTH_LONG).show();
            }

        });

    }


    private class AlbumRateOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String item = parent.getItemAtPosition(pos).toString();
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + item,
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }



}
