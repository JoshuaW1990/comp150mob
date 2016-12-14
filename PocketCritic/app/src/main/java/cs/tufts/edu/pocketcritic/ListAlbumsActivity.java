package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cs.tufts.edu.pocketcritic.model.Album;
import cs.tufts.edu.pocketcritic.model.AlbumSimple;
import cs.tufts.edu.pocketcritic.model.Artist;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.support.SpotifyApiAlbum;
import cs.tufts.edu.pocketcritic.support.SpotifyInterfaceAlbum;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListAlbumsActivity extends AppCompatActivity {

    private String queryString;
    private SpotifyApiAlbum spotifyApi;
    private SpotifyInterfaceAlbum spotifyInterface;
    private FirebaseDatabase mDatabase;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_albums);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        //artists = (List<Artist.ArtistsBean.ItemsBean>) intent.getSerializableExtra("artists");
        queryString = intent.getStringExtra("queryString");

        Button logout = (Button) findViewById(R.id.album_list_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListAlbumsActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ListAlbumsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance();


        spotifyApi = SpotifyApiAlbum.getApi();
        spotifyInterface = spotifyApi.getService();

        userID = getUid();

        queryByRxJava();

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
        spotifyInterface.getSpotifyResult(queryString, "album")
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
                            queryDatabase(albumList);

                        }
                    }
                });
    }

    private void queryDatabase(final List<Album.AlbumsBean.ItemsBean> albumList) {
        final DatabaseReference myRef = mDatabase.getReference("albums");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<AlbumSimple> albumSimpleList = new ArrayList<AlbumSimple>();
                for (final Album.AlbumsBean.ItemsBean album: albumList) {
                    if (!dataSnapshot.child(album.getId()).exists()) {
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
                        myRef.child(album.getId()).setValue(albumSimple);
                        albumSimpleList.add(albumSimple);
                    } else {
                        if (!dataSnapshot.child(album.getId()).child("id").exists()) {
                            myRef.child(album.getId()).child("id").setValue(album.getId());
                        }
                        if (!dataSnapshot.child(album.getId()).child("rate_num").exists()) {
                            myRef.child(album.getId()).child("rate_num").setValue(0.0);
                        }
                        if (!dataSnapshot.child(album.getId()).child("average_rate").exists()) {
                            myRef.child(album.getId()).child("average_rate").setValue(0.0);
                        }
                        if (!dataSnapshot.child(album.getId()).child("artist").exists()) {
                            myRef.child(album.getId()).child("artist").setValue(album.getArtists().get(0).getName());
                        }
                        AlbumSimple albumSimple = dataSnapshot.child(album.getId()).getValue(AlbumSimple.class);
                        albumSimpleList.add(albumSimple);
                    }
                }
                displayListView(albumSimpleList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void displayListView(List<AlbumSimple> albumList) {

        ListView listView = (ListView) findViewById(R.id.albumList_listview);
        Picasso.with(this).setIndicatorsEnabled(false);

        listView.setAdapter(new CommonAdapter< AlbumSimple >(this, albumList, R.layout.item_album_listview) {
            @Override
            public void convert(ViewHolder holder, AlbumSimple album, int position) {
                if (album.imageURL == "None") {
                    holder.setImage(R.id.album_img, R.drawable.placeholder_image, null);
                } else {
                    holder.setImage(R.id.album_img, album.imageURL, null);
                }
                holder.setText(R.id.album_name, album.name);
                holder.setText(R.id.album_artist, album.artist);

                long rate_num = (new Double(album.rate_num)).longValue();
                holder.setText(R.id.album_rating_score, Double.toString(album.average_rate));
                holder.setText(R.id.album_popularity_num, Long.toString(rate_num));

                RelativeLayout relativeLayout = holder.getView(R.id.album_relativelayout);
                relativeLayout.setClickable(true);


                relativeLayout.setOnClickListener(new ListButtonOnClickListener(album.id) {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, this.idnumber, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Intent intent = new Intent(ListAlbumsActivity.this, AlbumScrollingActivity.class);
                        intent.putExtra("searchId", this.idnumber);


                        //intent.putExtra("artists", "test");
                        startActivity(intent);
                    }
                });

            }
        });

    }

    private String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public class ListButtonOnClickListener implements View.OnClickListener
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
