package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;


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
import cs.tufts.edu.pocketcritic.support.SpotifyInterfaceAlbum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import android.app.Fragment;
//import android.app.FragmentManager;

public class ArtistScrollingActivity extends AppCompatActivity {

    private String searchId;
    private FirebaseDatabase database;
    private SpotifyArtistApi spotifyArtistApi;
    private SpotifyArtistInterface spotifyArtistInterface;
    private SpotifyApiAlbum spotifyAbumApi;
    private SpotifyInterfaceAlbum spotifyAlbumInterface;
    private static final String TAG = "ArtistInfo";
    private String userID;



    private FloatingActionButton upvote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.artist_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        searchId = intent.getStringExtra("searchId");

        database = FirebaseDatabase.getInstance();



        spotifyArtistApi = SpotifyArtistApi.getApi();
        spotifyArtistInterface = spotifyArtistApi.getService();

        spotifyAbumApi = SpotifyApiAlbum.getApi();
        spotifyAlbumInterface = spotifyAbumApi.getService();



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
                        writeDatatoDatabase(result);
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleArtist> call, Throwable t) {

            }
        });
    }

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

        TextView textView = (TextView) findViewById(R.id.artist_page_name);
        textView.setText(artist.name);
        ImageView imgView = (ImageView) findViewById(R.id.artist_page_img);
        Picasso.with(this).load(artist.imageURL).into(imgView);






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
    }




}
