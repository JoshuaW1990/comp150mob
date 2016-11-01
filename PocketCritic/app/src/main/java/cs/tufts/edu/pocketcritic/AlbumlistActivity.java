package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cs.tufts.edu.pocketcritic.models.Album;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;

public class AlbumlistActivity extends AppCompatActivity
        implements View.OnClickListener{

    //Button albumBack;
    List<Album> albums;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumlist);

        Intent intent = getIntent();
        albums = (List<Album>) intent.getSerializableExtra("albumlist");

//        albumBack = (Button) findViewById(R.id.albumList_back);
//        albumBack.setOnClickListener(this);

        displayListView();


    }

    private void displayListView() {

        ListView listView = (ListView) findViewById(R.id.albumList_listview);
        Picasso.with(this).setIndicatorsEnabled(true);

        listView.setAdapter(new CommonAdapter<Album>(this, albums, R.layout.album_listview) {
            @Override
            public void convert(ViewHolder holder, Album album, int position) {
                holder.setText(R.id.album_title, album.albumName);
                holder.setText(R.id.album_rating, Long.toString(album.albumRating));
                holder.setImage(R.id.album_img, album.albumCoverImageURL, null);
            }
        });

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
//        if (id == R.id.albumList_back) {
//            Intent intent = new Intent(AlbumlistActivity.this, ArtisitsActivity.class);
//            intent.putExtra("artistinfo", artistName);
//            startActivity(intent);
//            finish();
//        }

    }
}
