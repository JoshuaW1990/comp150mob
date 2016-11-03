package cs.tufts.edu.pocketcritic.fragment;

import cs.tufts.edu.pocketcritic.R;

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
import cs.tufts.edu.pocketcritic.support.SpotifyApi;
import cs.tufts.edu.pocketcritic.model.Artist;

import java.util.List;

/**
 * Created by junwang on 11/3/16.
 */

public class SearchFragment extends Fragment
        implements View.OnClickListener {

    private EditText searchInfo;
    private Button searchButton;

    private SpotifyApi spotifyApi;
    private SpotifyInterface spotifyInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search, container, false);


        // edit views
        searchInfo = (EditText) view.findViewById(R.id.search_searchinfo);

        // click listeners
        searchButton = (Button) view.findViewById(R.id.search_searchButton);
        searchButton.setOnClickListener(this);

        spotifyApi = SpotifyApi.getApi();
        spotifyInterface = spotifyApi.getService();

        return view;

    }

    public void doSearch() {
        System.out.println("do search here by spotify");
        System.out.println(searchInfo.getText().toString());

        query();


    }

    private String generateQueryString(String str) {
        return str.replaceAll("\\s+","+");
    }

    private void queryByRxJava() {
        String searchText = generateQueryString(searchInfo.getText().toString());
        if (searchText.isEmpty()) {
            Toast.makeText(getActivity(), "Please input artist name", Toast.LENGTH_SHORT).show();
            return;
        }
        spotifyInterface.getSpotifyResult(searchText, "artist")
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
                            List< Artist.ArtistsBean.ItemsBean > items = result.getArtists().getItems();
                            for (Artist.ArtistsBean.ItemsBean item: items) {
                                List<Artist.ArtistsBean.ItemsBean.ImagesBean> imageURLs = item.getImages();
                                for(Artist.ArtistsBean.ItemsBean.ImagesBean image: imageURLs) {
                                    String imageURL = image.getUrl();
                                    System.out.println(imageURL);
                                }
                            }
                        }
                    }
                });
    }


    private void query() {
        String searchText = generateQueryString(searchInfo.getText().toString());
        System.out.println(searchText);
        if (searchText.isEmpty()) {
            Toast.makeText(getActivity(), "Please input artist name", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<Artist> call = spotifyInterface.getResult(searchText, "artist");
        call.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if (response.isSuccessful()) {
                    Artist result = response.body();
                    if (result != null) {
                        List< Artist.ArtistsBean.ItemsBean > items = result.getArtists().getItems();
                        for (Artist.ArtistsBean.ItemsBean item: items) {
                            List<Artist.ArtistsBean.ItemsBean.ImagesBean> imageURLs = item.getImages();
                            for(Artist.ArtistsBean.ItemsBean.ImagesBean image: imageURLs) {
                                String imageURL = image.getUrl();
                                System.out.println(imageURL);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {

            }
        });
    }


    private void onSearchSuccess() {
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.search_searchButton) {
            doSearch();
        }
        else
        {

            System.out.println("error when searching!");
        }
    }
}
