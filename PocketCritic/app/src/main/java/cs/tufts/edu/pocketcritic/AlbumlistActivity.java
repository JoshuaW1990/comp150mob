package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ListView;

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

import cs.tufts.edu.pocketcritic.models.Album;
import cs.tufts.edu.pocketcritic.models.Artist;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.support.DownloadImageTask;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;

public class AlbumlistActivity extends AppCompatActivity
        implements View.OnClickListener{

    String artistName;
    Button albumBack;
    FirebaseDatabase database;
    List<Album> albums;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumlist);

        Intent intent = getIntent();
        albums = (List<Album>) intent.getSerializableExtra("albumlist");



        albumBack = (Button) findViewById(R.id.albumList_back);
        albumBack.setOnClickListener(this);


        //getAlbums(artistName);
        displayListView();


    }

    private void displayListView() {
        System.out.println("Entering displayListView function");

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



//        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.album_listview,
//                new String[] {"albumCover", "albumName", "albumRating"},
//                new int[] {R.id.album_img, R.id.album_title, R.id.album_rating});
//        ListView listview = (ListView) findViewById(R.id.albumList_listview);
//
//        listview.setAdapter(adapter);
//
//        for(int i = 0; i < adapter.getCount(); i++) {
//            HashMap<String, Object> map = (HashMap<String, Object>) adapter.getItem(i);
//            String imgURL = (String) map.get("albumCoverURL");
//
//
//
//        }

        System.out.println("Exiting displayListView function");


    }

    private List<Map<String, Object>> getData() {
        System.out.println("Entering getData function");
        List<Map<String, Object>> albumList = new ArrayList<Map<String, Object>>();


        for (Album album: albums) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("albumCover", R.drawable.androidicon);
            map.put("albumCoverURL", album.albumCoverImageURL);
            map.put("albumName", album.albumName);
            map.put("albumRating", album.albumRating);
            System.out.println(album.albumName);
            albumList.add(map);
            System.out.println(albumList.size());
        }

        System.out.println("Exiting getData function");

        return albumList;
    }

    public void getAlbums(String artistName) {
        albums = new ArrayList<Album>();
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("demoDatabase").child(artistName).child("3");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Start to read data from firebase");
                //List<Album> albumList = new ArrayList<Album>();
                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String albumName = child.getKey();
                    long albumRating = (long) child.child("0").getValue();
                    String albumCoverURL = (String) child.child("1").getValue();
                    Album album = new Album(albumName, albumRating, albumCoverURL);
                    albums.add(album);
                    System.out.println(album.albumName);
                    System.out.println(albums.size());

                }
                //saveAlbumList(albumList);
                displayListView();
                System.out.println("finish to read data from firebase");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String message = "Server error. Refresh page";
                Toast.makeText(AlbumlistActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void saveAlbumList(List<Album> albumList) {
        System.out.println("enter saveAlbumList function");
        System.out.println(albums.size());
        for (Album album: albumList)
        {
            albums.add(album);
            System.out.println(album.albumName);
            System.out.println(albums.size());
        }
        System.out.println("Get all albums");
        System.out.println(albums.get(0).albumName);
        System.out.println("exit saveAlbumList function");
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.albumList_back) {
            Intent intent = new Intent(AlbumlistActivity.this, ArtisitsActivity.class);
            intent.putExtra("artistinfo", artistName);
            startActivity(intent);
            finish();
        }

    }
}
