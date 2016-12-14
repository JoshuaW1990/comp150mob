package cs.tufts.edu.pocketcritic;

<<<<<<< HEAD
import android.os.Bundle;

import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Window;
import android.widget.ListView;

=======
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
>>>>>>> newSpotify
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
<<<<<<< HEAD

import android.widget.ImageView;
=======
import android.widget.Button;
import android.widget.ImageView;

import android.widget.RelativeLayout;
>>>>>>> newSpotify
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

=======
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.ListView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.List;

import cs.tufts.edu.pocketcritic.fragment.LoginFragment;
import cs.tufts.edu.pocketcritic.model.Artist;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.support.SpotifyApi;
import cs.tufts.edu.pocketcritic.support.SpotifyInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
>>>>>>> newSpotify
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

<<<<<<< HEAD

import cs.tufts.edu.pocketcritic.support.SpotifyInterface;
import cs.tufts.edu.pocketcritic.support.SpotifyApi;
import cs.tufts.edu.pocketcritic.model.Artist;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;

=======
>>>>>>> newSpotify
public class ListArtistsActivity extends AppCompatActivity {

    private String queryString;
    private SpotifyApi spotifyApi;
    private SpotifyInterface spotifyInterface;
<<<<<<< HEAD


//    public List< ArtistSimple > artists;
=======
    private FirebaseDatabase mDatabase;
    private String userID;
>>>>>>> newSpotify

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_artists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
<<<<<<< HEAD
        //artists = (List<Artist.ArtistsBean.ItemsBean>) intent.getSerializableExtra("artists");
        queryString = (String) intent.getStringExtra("queryString");
=======
        queryString = intent.getStringExtra("queryString");

        Button logout = (Button) findViewById(R.id.artist_list_logout);

        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListArtistsActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ListArtistsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance();
>>>>>>> newSpotify

        System.out.println("come to artist list");
        System.out.println(queryString);

        spotifyApi = SpotifyApi.getApi();
        spotifyInterface = spotifyApi.getService();

<<<<<<< HEAD
        //artists = new ArrayList<ArtistSimple>();
        queryByRxJava();
        //query();

//        query();
//        System.out.println("print the artist list");
//        for (ArtistSimple artist: artists) {
//            System.out.println(artist.bandName);
//        }

        //displayListView();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
=======
        userID = getUid();

        queryByRxJava();

>>>>>>> newSpotify
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

<<<<<<< HEAD
=======


>>>>>>> newSpotify
    private void queryByRxJava() {
        if (queryString.isEmpty()) {
            Toast.makeText(this, "Please input artist name", Toast.LENGTH_SHORT).show();
            return;
        }
        spotifyInterface.getSpotifyResult(queryString, "artist")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Artist>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Artist result) {
                        if (result != null) {
                            List<Artist.ArtistsBean.ItemsBean> artistList = result.getArtists().getItems();
<<<<<<< HEAD
                            displayListView(artistList);

=======
                            queryDatabase(artistList);
>>>>>>> newSpotify
                        }
                    }
                });
    }

<<<<<<< HEAD
    private void query() {
        if (queryString.isEmpty()) {
            Toast.makeText(this, "Please input artist name", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<Artist> call = spotifyInterface.getResult(queryString, "artist");
        call.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if (response.isSuccessful()) {
                    Artist result = response.body();
                    if (result != null) {
                        List< Artist.ArtistsBean.ItemsBean > artistList = result.getArtists().getItems();
                        displayListView(artistList);
                    }
                }
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
=======
    private void queryDatabase(final List<Artist.ArtistsBean.ItemsBean> artistList) {
        final DatabaseReference myRef = mDatabase.getReference("artists");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ArtistSimple> artistSimpleList = new ArrayList<ArtistSimple>();
                for (final Artist.ArtistsBean.ItemsBean artist: artistList) {
                    if (!dataSnapshot.child(artist.getId()).exists()) {
                        String id = artist.getId();
                        String name = artist.getName();
                        String imageURL;
                        if (artist.getImages().size() > 0) {
                            imageURL = artist.getImages().get(0).getUrl();
                        } else {
                            imageURL = "None";
                        }
                        ArtistSimple artistSimple = new ArtistSimple(id, name, imageURL);
                        myRef.child(artist.getId()).setValue(artistSimple);
                        System.out.println("artist imageURL");
                        System.out.println(imageURL);
                        artistSimpleList.add(artistSimple);
                    } else {
                        if (!dataSnapshot.child(artist.getId()).child("id").exists()) {
                            myRef.child(artist.getId()).child("id").setValue(artist.getId());
                        }
                        ArtistSimple artistSimple = dataSnapshot.child(artist.getId()).getValue(ArtistSimple.class);
                        artistSimpleList.add(artistSimple);
                    }
                }
                System.out.println(artistSimpleList.size());
                displayListView(artistSimpleList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
>>>>>>> newSpotify

            }
        });
    }

<<<<<<< HEAD
//    private void setArtists(List<Artist.ArtistsBean.ItemsBean> artistList) {
//        for (Artist.ArtistsBean.ItemsBean artist: artistList) {
//            ArtistSimple tmpArtist = new ArtistSimple(artist.getName(),
//                    artist.getImages().get(0).getUrl(), artist.getGenres(), artist.getId(), artist.getPopularity());
//            //System.out.println(tmpArtist.bandName);
//            this.artists.add(tmpArtist);
//            System.out.println(tmpArtist.bandName);
//            System.out.println(artists.size());
//        }
//    }

    private void displayListView(List<Artist.ArtistsBean.ItemsBean> artistList) {
=======





    private void displayListView(List<ArtistSimple> artistList) {
        if (artistList.size() > 0) {
            System.out.println("Start to display the list view");
        }
>>>>>>> newSpotify

        ListView listView = (ListView) findViewById(R.id.artistList_listview);
        Picasso.with(this).setIndicatorsEnabled(false);

<<<<<<< HEAD
        listView.setAdapter(new CommonAdapter< Artist.ArtistsBean.ItemsBean >(this, artistList, R.layout.artist_listview) {
            @Override
            public void convert(ViewHolder holder, Artist.ArtistsBean.ItemsBean artist, int position) {
                int size = artist.getImages().size();
                if (size == 0) {
                    holder.setImage(R.id.artist_img, R.drawable.androidicon, null);
                } else {
                    holder.setImage(R.id.artist_img, artist.getImages().get(size - 1).getUrl(), null);
                }
                holder.setText(R.id.artist_name, artist.getName());
                holder.setText(R.id.artist_rating, Integer.toString(artist.getPopularity()));
                ImageView imgView = holder.getView(R.id.artist_img);
                imgView.setClickable(true);


//                imgView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//                });

                imgView.setOnClickListener(new ListButtonOnClickListener(artist.getId()) {
=======
        listView.setAdapter(new CommonAdapter< ArtistSimple >(this, artistList, R.layout.item_artist_listview) {
            @Override
            public void convert(ViewHolder holder, ArtistSimple artist, int position) {
                if (artist.imageURL == "None") {
                    holder.setImage(R.id.artist_img, R.drawable.placeholder_image, null);
                } else {
                    holder.setImage(R.id.artist_img, artist.imageURL, null);
                }
                holder.setText(R.id.artist_name, artist.name);
                holder.setText(R.id.item_num_stars, Integer.toString(artist.popularity));


                if (artist.stars.containsKey(userID)) {
                    holder.setImage(R.id.item_star, R.drawable.ic_toggle_star_24, null);
                } else {
                    holder.setImage(R.id.item_star, R.drawable.ic_toggle_star_outline_24, null);
                }


                RelativeLayout relativeLayout = holder.getView(R.id.artist_relativeLayout);
                relativeLayout.setClickable(true);
                relativeLayout.setOnClickListener(new ListButtonOnClickListener(artist.id) {
>>>>>>> newSpotify
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, this.idnumber, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
<<<<<<< HEAD
                        Intent intent = new Intent(ListArtistsActivity.this, ArtistScrollingActivity.class);
                        intent.putExtra("searchId", this.idnumber);


                        //intent.putExtra("artists", "test");
                        startActivity(intent);
                        System.out.println("On search success!");
=======

                        Intent intent = new Intent(ListArtistsActivity.this, ArtistScrollingActivity.class);
                        intent.putExtra("searchId", this.idnumber);
                        System.out.println("go to the scrolling view");
                        System.out.println(this.idnumber);
                        startActivity(intent);

>>>>>>> newSpotify
                    }
                });


            }
        });

    }

<<<<<<< HEAD
=======
    private String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

>>>>>>> newSpotify
    public class ListButtonOnClickListener implements OnClickListener
    {

        String idnumber;
        public ListButtonOnClickListener(String idnumber) {
            this.idnumber = idnumber;
        }

        @Override
        public void onClick(View v)
        {
            //Do your stuff
        }

    };




}
