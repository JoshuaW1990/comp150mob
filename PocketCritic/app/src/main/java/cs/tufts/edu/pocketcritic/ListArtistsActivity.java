package cs.tufts.edu.pocketcritic;

import android.os.Bundle;

import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.ListView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


import cs.tufts.edu.pocketcritic.support.SpotifyInterface;
import cs.tufts.edu.pocketcritic.support.SpotifyApi;
import cs.tufts.edu.pocketcritic.model.Artist;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;

public class ListArtistsActivity extends AppCompatActivity {

    private String queryString;
    private SpotifyApi spotifyApi;
    private SpotifyInterface spotifyInterface;


//    public List< ArtistSimple > artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_artists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        //artists = (List<Artist.ArtistsBean.ItemsBean>) intent.getSerializableExtra("artists");
        queryString = (String) intent.getStringExtra("queryString");

        System.out.println("come to artist list");
        System.out.println(queryString);

        spotifyApi = SpotifyApi.getApi();
        spotifyInterface = spotifyApi.getService();

        //artists = new ArrayList<ArtistSimple>();
        queryByRxJava();
        //query();

//        query();
//        System.out.println("print the artist list");
//        for (ArtistSimple artist: artists) {
//            System.out.println(artist.bandName);
//        }

        //displayListView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                            displayListView(artistList);

                        }
                    }
                });
    }

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

            }
        });
    }

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

        ListView listView = (ListView) findViewById(R.id.artistList_listview);
        Picasso.with(this).setIndicatorsEnabled(false);

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
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, this.idnumber, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Intent intent = new Intent(ListArtistsActivity.this, ArtistScrollingActivity.class);
                        intent.putExtra("searchId", this.idnumber);


                        //intent.putExtra("artists", "test");
                        startActivity(intent);
                        System.out.println("On search success!");
                    }
                });


            }
        });

    }

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
