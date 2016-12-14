package cs.tufts.edu.pocketcritic;

import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Color;
import android.os.Bundle;
=======
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
>>>>>>> newSpotify
import android.util.Log;

import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

<<<<<<< HEAD
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

=======

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
>>>>>>> newSpotify
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
<<<<<<< HEAD
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import cs.tufts.edu.pocketcritic.model.Album;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.support.SpotifyApiAlbum;
=======
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs.tufts.edu.pocketcritic.fragment.ArtistAlbumListFragment;
import cs.tufts.edu.pocketcritic.fragment.ArtistCommentListFragment;
import cs.tufts.edu.pocketcritic.model.Album;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.model.SingleArtist;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.support.PagerAdapter;
import cs.tufts.edu.pocketcritic.support.SpotifyApiAlbum;
import cs.tufts.edu.pocketcritic.support.SpotifyArtistApi;
import cs.tufts.edu.pocketcritic.support.SpotifyArtistInterface;
>>>>>>> newSpotify
import cs.tufts.edu.pocketcritic.support.SpotifyInterfaceAlbum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

<<<<<<< HEAD
import cs.tufts.edu.pocketcritic.model.SingleArtist;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.support.SpotifyArtistApi;
import cs.tufts.edu.pocketcritic.support.SpotifyArtistInterface;
=======
//import android.app.Fragment;
//import android.app.FragmentManager;
>>>>>>> newSpotify

public class ArtistScrollingActivity extends AppCompatActivity {

    private String searchId;
    private FirebaseDatabase database;
    private SpotifyArtistApi spotifyArtistApi;
    private SpotifyArtistInterface spotifyArtistInterface;
    private SpotifyApiAlbum spotifyAbumApi;
    private SpotifyInterfaceAlbum spotifyAlbumInterface;
    private static final String TAG = "ArtistInfo";
<<<<<<< HEAD
=======
    private String userID;



    private FloatingActionButton upvote;
>>>>>>> newSpotify

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.artist_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        searchId = intent.getStringExtra("searchId");

<<<<<<< HEAD
        System.out.println("come to artist page");
        System.out.println(searchId);
=======
        database = FirebaseDatabase.getInstance();


>>>>>>> newSpotify

        spotifyArtistApi = SpotifyArtistApi.getApi();
        spotifyArtistInterface = spotifyArtistApi.getService();

        spotifyAbumApi = SpotifyApiAlbum.getApi();
        spotifyAlbumInterface = spotifyAbumApi.getService();


<<<<<<< HEAD
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

=======

        searchDatabaseById();

        upvote = (FloatingActionButton) findViewById(R.id.artist_upvote);
        userID = getUid();
        final DatabaseReference myRef = database.getReference("artists").child(searchId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    upvote.setImageResource(R.drawable.ic_toggle_star_outline_24);
                } else {
                    ArtistSimple artistSimple = dataSnapshot.getValue(ArtistSimple.class);
                    if (!artistSimple.stars.containsKey(getUid())) {
                        upvote.setImageResource(R.drawable.ic_toggle_star_outline_24);
                    } else {
                        upvote.setImageResource(R.drawable.ic_toggle_star_24);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        upvote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatabaseReference artRef = database.getReference("artists").child(searchId);
                onStarClicked(artRef);
            }
        });


        FloatingActionButton postComment = (FloatingActionButton) findViewById(R.id.artist_fab_new_comment);
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArtistScrollingActivity.this, NewCommentActivity.class);
                String [] info = {searchId, "artists"};
                intent.putExtra("commentinfo", info);
                startActivity(intent);
            }
        });
    }




    private void searchDatabaseById() {
        DatabaseReference myRef = database.getReference("artists").child(searchId);
        // Read from the database
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ArtistSimple artist = dataSnapshot.getValue(ArtistSimple.class);
                    initView(artist, searchId);
                }
                else {
                    searchSpotifyById();
                }
>>>>>>> newSpotify
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
<<<<<<< HEAD
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
=======
                        writeDatatoDatabase(result);
>>>>>>> newSpotify
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleArtist> call, Throwable t) {

            }
        });
    }

<<<<<<< HEAD
    private void initView(ArtistSimple artist) {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.artist_collapsing_toolbar_layout);
        //collapsingToolbar.setCollapsedTitleTextColor(Color.TRANSPARENT);
=======
    private void writeDatatoDatabase(SingleArtist artist) {
        // Get the data to form the class of ArtistSimple
        String id = artist.getId();
        String name = artist.getName();
        String imageURL;
        if (artist.getImages().size() > 0) {
            imageURL = artist.getImages().get(0).getUrl();
        } else {
            imageURL = "None";
        }
        ArtistSimple artistSimple = new ArtistSimple(id, name, imageURL);

        // Write the data to the artists database
        Map<String, Object> artistValue = artistSimple.toMap();

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/artists/" + searchId, artistValue);

        DatabaseReference myRef = database.getReference();
        myRef.updateChildren(childUpdates);
        initView(artistSimple, searchId);
    }




    private void initView(ArtistSimple artist, String artistId) {
>>>>>>> newSpotify

        TextView textView = (TextView) findViewById(R.id.artist_page_name);
        textView.setText(artist.name);
        ImageView imgView = (ImageView) findViewById(R.id.artist_page_img);
        Picasso.with(this).load(artist.imageURL).into(imgView);

<<<<<<< HEAD
        queryByRxJava(artist.name);

    }
=======

>>>>>>> newSpotify




<<<<<<< HEAD





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

=======
        TabLayout tabLayout = (TabLayout) findViewById(R.id.artist_tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.artist_pager);


        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new ArtistAlbumListFragment(), "Albums", artist.name, false);  // may have bugs
        adapter.addFragment(new ArtistCommentListFragment(), "Comments", artistId, false);
        System.out.println(viewPager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }



    private void onStarClicked(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                ArtistSimple artistSimple = mutableData.getValue(ArtistSimple.class);
                System.out.println("on Star click");
                if (artistSimple == null) {
                    return Transaction.success(mutableData);
                }

                if (artistSimple.stars.containsKey(getUid())) {
                    // Unstar the post and remove self from stars
                    artistSimple.popularity = artistSimple.popularity - 1;
                    artistSimple.stars.remove(getUid());
                } else {
                    // Star the post and add self to stars
                    artistSimple.popularity = artistSimple.popularity + 1;
                    artistSimple.stars.put(getUid(), true);
                }

                // Set value and report transaction success
                mutableData.setValue(artistSimple);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }



    public  class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public BaseFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title, String data) {
            addFragment(fragment, title, data, true);
        }

        public void addFragment(Fragment fragment, String title, String data, boolean hasOptionsMenu) {
            Bundle bundle=new Bundle();
            bundle.putString("searchInfo",data);
            fragment.setHasOptionsMenu(hasOptionsMenu);
            fragment.setArguments(bundle);
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
>>>>>>> newSpotify
    }




}
