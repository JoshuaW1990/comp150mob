package cs.tufts.edu.pocketcritic.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import cs.tufts.edu.pocketcritic.R;
import cs.tufts.edu.pocketcritic.model.Album;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artist_tabalbumlist, container, false);
        artistName = getArguments().getString("searchInfo");

        spotifyAbumApi = SpotifyApiAlbum.getApi();
        spotifyAlbumInterface = spotifyAbumApi.getService();

        System.out.println(artistName);
        queryByRxJava(artistName);

        return view;
    }

    private String generateQueryString(String str) {
        return str.replaceAll("\\s+","+");
    }

    private void queryByRxJava(String searchInfo) {
        String queryString = generateQueryString(searchInfo);
        System.out.println(queryString);
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

        ListView listView = (ListView) view.findViewById(R.id.artist_albumList);
        Picasso.with(getActivity()).setIndicatorsEnabled(false);

        listView.setAdapter(new CommonAdapter< Album.AlbumsBean.ItemsBean >(getActivity(), albumList, R.layout.artist_albumlistview) {
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

    }



}
