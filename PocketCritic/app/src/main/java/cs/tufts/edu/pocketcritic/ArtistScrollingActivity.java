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
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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


        FloatingActionButton upvote = (FloatingActionButton) findViewById(R.id.artist_upvote);
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mFirebaseAuth;
                mFirebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                String userID = mFirebaseUser.getUid();

                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("userLiked").child(userID).child(searchId);
                myRef.setValue(0);

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
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("1");

        myRef = database.getReference("artists").child(searchId);

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

        TextView textView = (TextView) findViewById(R.id.artist_page_name);
        textView.setText(artist.name);
        ImageView imgView = (ImageView) findViewById(R.id.artist_page_img);
        Picasso.with(this).load(artist.imageURL).into(imgView);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.artist_tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.artist_pager);

        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ArtistAlbumListFragment(), "Albums", artist.name, false);
        adapter.addFragment(new ArtistCommentListFragment(), "Comments", artist.name, false);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }





    public  class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        private String databaseInfo;

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
            databaseInfo = data;
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
