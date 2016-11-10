package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

//import android.app.Fragment;
//import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.CollapsingToolbarLayout;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import cs.tufts.edu.pocketcritic.model.Album;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.support.SpotifyApiAlbum;
import cs.tufts.edu.pocketcritic.support.SpotifyInterfaceAlbum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import cs.tufts.edu.pocketcritic.model.SingleArtist;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.support.SpotifyArtistApi;
import cs.tufts.edu.pocketcritic.support.SpotifyArtistInterface;

public class ArtistScrollingActivity extends AppCompatActivity {

    private String searchId;
    private FirebaseDatabase database;
    private SpotifyArtistApi spotifyArtistApi;
    private SpotifyArtistInterface spotifyArtistInterface;
    private SpotifyApiAlbum spotifyAbumApi;
    private SpotifyInterfaceAlbum spotifyAlbumInterface;
    private static final String TAG = "ArtistInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        searchId = intent.getStringExtra("searchId");

        System.out.println("come to artist page");
        System.out.println(searchId);

        spotifyArtistApi = SpotifyArtistApi.getApi();
        spotifyArtistInterface = spotifyArtistApi.getService();

        spotifyAbumApi = SpotifyApiAlbum.getApi();
        spotifyAlbumInterface = spotifyAbumApi.getService();


        searchDatabaseById();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("1");

        myRef = database.getReference("test").child(searchId);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String value = dataSnapshot.getKey();
                    Log.d(TAG, "Value is: " + value);
                    ArtistSimple artist = dataSnapshot.getValue(ArtistSimple.class);
                    initView(artist);
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
        Call<SingleArtist> call = spotifyArtistInterface.getResult(searchId);
        call.enqueue(new Callback<SingleArtist>() {
            @Override
            public void onResponse(Call<SingleArtist> call, Response<SingleArtist> response) {
                if (response.isSuccessful()) {
                    SingleArtist result = response.body();
                    if (result != null) {
                        System.out.println(result.getName());
                        DatabaseReference myRef = database.getReference("test").child(searchId);
                        String name = result.getName();
                        int popularity = result.getPopularity();
                        String imageURL;
                        if (result.getImages().size() > 0) {
                            imageURL = result.getImages().get(0).getUrl();
                        } else {
                            imageURL = "None";
                        }
                        ArtistSimple artist = new ArtistSimple(name, imageURL, popularity);
                        myRef.setValue(artist);
                        initView(artist);
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleArtist> call, Throwable t) {

            }
        });
    }

    private void initView(ArtistSimple artist) {
//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.artist_collapsing_toolbar_layout);
//        collapsingToolbar.setTitle(artist.name);
        TextView textView = (TextView) findViewById(R.id.artist_page_name);
        textView.setText(artist.name);
        ImageView imgView = (ImageView) findViewById(R.id.artist_page_img);
        Picasso.with(this).load(artist.imageURL).into(imgView);

        queryByRxJava(artist.name);

    }









    private void queryByRxJava(String artistName) {
        String queryString = generateQueryString(artistName);
        spotifyAlbumInterface.getSpotifyResult(queryString, "album")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Album>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Album result) {
                        if (result != null) {
                            List<Album.AlbumsBean.ItemsBean> albumList = result.getAlbums().getItems();
                            System.out.println("number of albums: ");
                            System.out.println(albumList.size());
                            for (Album.AlbumsBean.ItemsBean album: albumList) {
                                System.out.println(album.getName());
                            }
                            displayListView(albumList);

                        }
                    }
                });
    }

    private void displayListView(List<Album.AlbumsBean.ItemsBean> albumList) {

        ListView listView = (ListView) findViewById(R.id.artist_albumList);
        Picasso.with(this).setIndicatorsEnabled(false);

        listView.setAdapter(new CommonAdapter< Album.AlbumsBean.ItemsBean >(this, albumList, R.layout.artist_albumlistview) {
            @Override
            public void convert(ViewHolder holder, Album.AlbumsBean.ItemsBean album, int position) {
                int size = album.getImages().size();
                if (size == 0) {
                    holder.setImage(R.id.artist_album_img, R.drawable.placeholder_image, null);
                } else {
                    holder.setImage(R.id.artist_album_img, album.getImages().get(size - 1).getUrl(), null);
                }
                holder.setText(R.id.artist_album_name, album.getName());

            }
        });

    }




}
