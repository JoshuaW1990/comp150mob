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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import cs.tufts.edu.pocketcritic.model.Album;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_albums);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        //artists = (List<Artist.ArtistsBean.ItemsBean>) intent.getSerializableExtra("artists");
        queryString = intent.getStringExtra("queryString");

        System.out.println("come to artist list");
        System.out.println(queryString);

        spotifyApi = SpotifyApiAlbum.getApi();
        spotifyInterface = spotifyApi.getService();

        queryByRxJava();


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
                            displayListView(albumList);

                        }
                    }
                });
    }

    private void displayListView(List<Album.AlbumsBean.ItemsBean> albumList) {

        ListView listView = (ListView) findViewById(R.id.albumList_listview);
        Picasso.with(this).setIndicatorsEnabled(false);

        listView.setAdapter(new CommonAdapter< Album.AlbumsBean.ItemsBean >(this, albumList, R.layout.album_listview) {
            @Override
            public void convert(ViewHolder holder, Album.AlbumsBean.ItemsBean album, int position) {
                int size = album.getImages().size();
                if (size == 0) {
                    holder.setImage(R.id.album_img, R.drawable.placeholder_image, null);
                } else {
                    holder.setImage(R.id.album_img, album.getImages().get(size - 1).getUrl(), null);
                }
                holder.setText(R.id.album_name, album.getName());
                holder.setText(R.id.album_artist, album.getArtists().get(0).getName());


            }
        });

    }




}
