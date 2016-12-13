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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs.tufts.edu.pocketcritic.model.Album;
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
    private ArrayAdapter<String> dataAdapter;
    private Map<String, Double> score = new HashMap<>();
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
        database = FirebaseDatabase.getInstance();
        rateSpinner = (Spinner) findViewById(R.id.album_rating_spinner);

        score.put("Awful", 0.0);
        score.put("Poor", 1.0);
        score.put("Good", 2.0);
        score.put("Excellent", 3.0);
        score.put("Classic", 4.0);

        List<String> list = new ArrayList<>();
        list.add("Rate It");
        list.add("Awful");
        list.add("Poor");
        list.add("Good");
        list.add("Excellent");
        list.add("Classic");

        dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        rateSpinner.setAdapter(dataAdapter);

        final DatabaseReference myRef = database.getReference("albums").child(searchId);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    rateSpinner.setSelection(dataAdapter.getPosition("Rate It"));
                } else {
                    AlbumSimple albumSimple = dataSnapshot.getValue(AlbumSimple.class);
                    if (!albumSimple.stars.containsKey(userID)) {
                        rateSpinner.setSelection(dataAdapter.getPosition("Rate It"));
                    } else {
                        double rate = albumSimple.stars.get(userID);
                        if (rate == 0.0) {
                            rateSpinner.setSelection(dataAdapter.getPosition("Awful"));
                        } else if (rate == 1.0) {
                            rateSpinner.setSelection(dataAdapter.getPosition("Poor"));
                        } else if (rate == 2.0) {
                            rateSpinner.setSelection(dataAdapter.getPosition("Good"));
                        } else if (rate == 3.0) {
                            rateSpinner.setSelection(dataAdapter.getPosition("Excellent"));
                        } else {
                            rateSpinner.setSelection(dataAdapter.getPosition("Classic"));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        // Spinner item selection Listener
        addListenerOnSpinnerItemSelection();

        // Button click Listener
        Button btnSubmit = (Button) findViewById(R.id.album_submit_rate);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatabaseReference albumRef = database.getReference("albums").child(searchId);
                String item = String.valueOf(rateSpinner.getSelectedItem());
                if (item.equals("Rate It")) {
                    Toast.makeText(AlbumScrollingActivity.this, "Pick a rate", Toast.LENGTH_SHORT).show();
                } else {
                    onRateSubmit(albumRef, item);
                    rateSpinner.setSelection(dataAdapter.getPosition(item));
                    Toast.makeText(AlbumScrollingActivity.this, "On Button Click : " + "\n" + item , Toast.LENGTH_LONG).show();
                }

            }

        });


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



    private void searchDatabaseById() {
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

    }

    // Add spinner data

    public void addListenerOnSpinnerItemSelection(){

        rateSpinner.setOnItemSelectedListener(new AlbumRateOnItemSelectedListener());
    }



    private void onRateSubmit(DatabaseReference albumRef, final String item) {
        albumRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                AlbumSimple albumSimple = mutableData.getValue(AlbumSimple.class);
                System.out.println("on submit click");
                if (albumSimple == null) {
                    System.out.println("empty album object");
                    return Transaction.success(mutableData);
                }
                if (albumSimple.stars.containsKey(userID)) {
                    double  rateNum = albumSimple.rate_num;
                    double averageRate = albumSimple.average_rate;
                    averageRate = (averageRate * rateNum - albumSimple.stars.get(userID) + score.get(item)) / rateNum;
                    albumSimple.average_rate = averageRate;
                    albumSimple.rate_num = rateNum;
                } else {
                    double  rateNum = albumSimple.rate_num;
                    double averageRate = (rateNum * albumSimple.average_rate + score.get(item)) / (rateNum + 1.0);
                    albumSimple.rate_num = rateNum + 1.0;
                    albumSimple.average_rate = averageRate;
                }
                albumSimple.stars.put(userID, score.get(item));

                mutableData.setValue(albumSimple);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                Log.d(TAG, "rateTransaction:onComplete:" + databaseError);
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
