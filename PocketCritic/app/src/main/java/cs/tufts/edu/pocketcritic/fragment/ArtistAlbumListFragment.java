package cs.tufts.edu.pocketcritic.fragment;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cs.tufts.edu.pocketcritic.AlbumScrollingActivity;
import cs.tufts.edu.pocketcritic.ListAlbumsActivity;
import cs.tufts.edu.pocketcritic.R;
import cs.tufts.edu.pocketcritic.model.Album;
import cs.tufts.edu.pocketcritic.model.AlbumSimple;
import cs.tufts.edu.pocketcritic.support.CommonAdapter;
import cs.tufts.edu.pocketcritic.support.SpotifyApiAlbum;
import cs.tufts.edu.pocketcritic.support.SpotifyInterfaceAlbum;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by junwang on 11/19/16.
 */

public class ArtistAlbumListFragment extends Fragment {

    private View view;
    private String artistName;
    private SpotifyApiAlbum spotifyAbumApi;
    private SpotifyInterfaceAlbum spotifyAlbumInterface;
    private FirebaseDatabase mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artist_tabalbumlist, container, false);
        artistName = getArguments().getString("searchInfo");

        mDatabase = FirebaseDatabase.getInstance();
        spotifyAbumApi = SpotifyApiAlbum.getApi();
        spotifyAlbumInterface = spotifyAbumApi.getService();

        queryByRxJava(artistName);

        return view;
    }

    private String generateQueryString(String str) {
        return str.replaceAll("\\s+","+");
    }

    private void queryByRxJava(String searchInfo) {
        String queryString = generateQueryString(searchInfo);
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
                        System.out.println("album imageURL");
                        System.out.println(imageURL);
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
                System.out.println(albumSimpleList.size());
                displayListView(albumSimpleList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void displayListView(List<AlbumSimple> albumList) {

        ListView listView = (ListView) view.findViewById(R.id.artist_albumList);
        Picasso.with(getActivity()).setIndicatorsEnabled(false);

        listView.setAdapter(new CommonAdapter< AlbumSimple >(getActivity(), albumList, R.layout.artist_albumlistview) {
            @Override
            public void convert(ViewHolder holder, AlbumSimple album, int position) {
                if (album.imageURL == "None") {
                    holder.setImage(R.id.artist_album_img, R.drawable.placeholder_image, null);
                } else {
                    holder.setImage(R.id.artist_album_img, album.imageURL, null);
                }
                holder.setText(R.id.artist_album_name, album.name);
                holder.setText(R.id.artist_album_rating_score, Double.toString(album.average_rate));

                RelativeLayout relativeLayout = holder.getView(R.id.artist_album_relativelayout);
                relativeLayout.setClickable(true);

                relativeLayout.setOnClickListener(new AlbumButtonOnClickListener(album.id) {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, this.idnumber, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Intent intent = new Intent(getActivity(), AlbumScrollingActivity.class);
                        intent.putExtra("searchId", this.idnumber);


                        //intent.putExtra("artists", "test");
                        startActivity(intent);
                        System.out.println("On search success!");
                    }
                });

            }
        });

    }

    public class AlbumButtonOnClickListener implements View.OnClickListener
    {

        String idnumber;
        public AlbumButtonOnClickListener(String idnumber) {
            this.idnumber = idnumber;
        }

        @Override
        public void onClick(View v)
        {
            //Do your stuff
        }

    };




}
