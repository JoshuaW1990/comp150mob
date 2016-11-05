package cs.tufts.edu.pocketcritic.support;

/**
 * Created by joshuaw on 11/4/16.
 */


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class SpotifyApiAlbum {
    public static final String BASE_URL = "https://api.spotify.com";


    public static SpotifyApiAlbum getApi(){
        return ApiHolder.spotifyApiAlbum;
    }

    static class ApiHolder{
        private static SpotifyApiAlbum spotifyApiAlbum = new SpotifyApiAlbum();
    }

    private SpotifyInterfaceAlbum service;

    private SpotifyApiAlbum(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SpotifyInterfaceAlbum.class);
    }

    public SpotifyInterfaceAlbum getService(){
        return service;
    }

}
