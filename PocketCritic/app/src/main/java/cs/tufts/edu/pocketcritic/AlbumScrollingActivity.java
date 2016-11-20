package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cs.tufts.edu.pocketcritic.model.AlbumSimple;
import cs.tufts.edu.pocketcritic.model.SingleAlbum;
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



        searchDatabaseById();

        FloatingActionButton upvote = (FloatingActionButton) findViewById(R.id.upvote);
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mFirebaseAuth;
                mFirebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                String userID = mFirebaseUser.getUid();

                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users").child(userID).child("LikedArtists");
                myRef.setValue(searchId);

            }
        });

        FloatingActionButton downvote = (FloatingActionButton) findViewById(R.id.downvote);
        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mFirebaseAuth;
                mFirebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                String userID = mFirebaseUser.getUid();

                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users").child(userID).child("DisLikedArtists");
                myRef.setValue(searchId);

            }
        });

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
                    String value = dataSnapshot.getKey();
                    Log.d(TAG, "Value is: " + value);
                    AlbumSimple album = dataSnapshot.getValue(AlbumSimple.class);
                    initView(album);
                }
                else {
//                    Toast.makeText(ArtistScrollingActivity.this, "Not found",
//                            Toast.LENGTH_SHORT).show();
                    System.out.println("not foud");
                    searchSpotifyById();
                    //System.out.println(singleartist.getName());
                }
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

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
                        System.out.println(result.getName());
                        DatabaseReference myRef = database.getReference("albums").child(searchId);
                        String name = result.getName();
                        String imageURL;
                        if (result.getImages().size() > 0) {
                            imageURL = result.getImages().get(0).getUrl();
                        } else {
                            imageURL = "None";
                        }
                        AlbumSimple album = new AlbumSimple(name, imageURL);
                        myRef.setValue(album);
                        initView(album);
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleAlbum> call, Throwable t) {

            }
        });
    }

    private void initView(AlbumSimple album) {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.album_collapsing_toolbar_layout);
        //collapsingToolbar.setCollapsedTitleTextColor(Color.TRANSPARENT);

        TextView textView = (TextView) findViewById(R.id.album_page_name);
        textView.setText(album.name);
        ImageView imgView = (ImageView) findViewById(R.id.album_page_img);
        Picasso.with(this).load(album.imageURL).into(imgView);


    }







}
