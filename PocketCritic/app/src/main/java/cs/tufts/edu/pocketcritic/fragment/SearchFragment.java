package cs.tufts.edu.pocketcritic.fragment;

import cs.tufts.edu.pocketcritic.ListActivity;
import cs.tufts.edu.pocketcritic.ListAlbumsActivity;
import cs.tufts.edu.pocketcritic.ListArtistsActivity;
import cs.tufts.edu.pocketcritic.MainActivity;
import cs.tufts.edu.pocketcritic.R;

import android.content.Intent;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import cs.tufts.edu.pocketcritic.support.SpotifyInterface;
import cs.tufts.edu.pocketcritic.support.SpotifyInterfaceAlbum;
import cs.tufts.edu.pocketcritic.support.SpotifyApi;
import cs.tufts.edu.pocketcritic.support.SpotifyApiAlbum;
import cs.tufts.edu.pocketcritic.model.Artist;
import cs.tufts.edu.pocketcritic.model.Album;

import java.io.Serializable;

import java.util.List;

/**
 * Created by junwang on 11/3/16.
 */

public class SearchFragment extends Fragment
        implements View.OnClickListener {

    private EditText searchInfo;
    private Button searchArtist;
    private Button searchAlbum;

    private SpotifyApi spotifyApi;
    private SpotifyApiAlbum spotifyApiAlbum;
    private SpotifyInterface spotifyInterface;
    private SpotifyInterfaceAlbum spotifyInterfaceAlbum;

    private String queryString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search, container, false);


        // edit views
        searchInfo = (EditText) view.findViewById(R.id.search_searchinfo);


        // click listeners
        searchArtist = (Button) view.findViewById(R.id.search_searchArtist);
        searchArtist.setOnClickListener(this);
        searchAlbum = (Button) view.findViewById(R.id.search_searchAlbum);
        searchAlbum.setOnClickListener(this);

        spotifyApi = SpotifyApi.getApi();
        spotifyInterface = spotifyApi.getService();
        spotifyApiAlbum = SpotifyApiAlbum.getApi();
        spotifyInterfaceAlbum = spotifyApiAlbum.getService();

        return view;

    }

    public void ArtistSearch() {
        queryString = generateQueryString(searchInfo.getText().toString());

        queryByRxJavaArtist();


    }

    public void AlbumSearch() {
        queryString = generateQueryString(searchInfo.getText().toString());

        queryByRxJavaAlbum();


    }

    private String generateQueryString(String str) {
        return str.replaceAll("\\s+","+");
    }

    private void queryByRxJavaArtist() {
        if (queryString.isEmpty()) {
            Toast.makeText(getActivity(), "Please input artist name", Toast.LENGTH_SHORT).show();
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
                            List< Artist.ArtistsBean.ItemsBean > artists = result.getArtists().getItems();
                            if (artists.size() == 0) {
                                Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
                            } else {
                                onSearchArtistSuccess();
                            }
                        }
                    }
                });
    }


    private void queryByRxJavaAlbum() {
        if (queryString.isEmpty()) {
            Toast.makeText(getActivity(), "Please input album name", Toast.LENGTH_SHORT).show();
            return;
        }
        spotifyInterfaceAlbum.getSpotifyResult(queryString, "album")
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
                            List< Album.AlbumsBean.ItemsBean > albums = result.getAlbums().getItems();
                            if (albums.size() == 0) {
                                Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
                            } else {
                                onSearchAlbumSuccess();
                            }
                        }
                    }
                });
    }






    private void onSearchArtistSuccess() {

        Intent intent = new Intent(getActivity(), ListArtistsActivity.class);
        intent.putExtra("queryString", queryString);

        //intent.putExtra("artists", "test");
        startActivity(intent);


    }

    private void onSearchAlbumSuccess() {

        Intent intent = new Intent(getActivity(), ListAlbumsActivity.class);
        intent.putExtra("queryString", queryString);

        startActivity(intent);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.search_searchArtist) {
            ArtistSearch();
        }
        else if (id == R.id.search_searchAlbum)
        {
            AlbumSearch();
        }
    }
}
