package cs.tufts.edu.pocketcritic.support;

/**
 * Created by junwang on 11/3/16.
 */


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class SpotifyApi {
    public static final String BASE_URL = "https://api.spotify.com";


    public static SpotifyApi getApi(){
        return ApiHolder.spotifyApi;
    }

    static class ApiHolder{
        private static SpotifyApi spotifyApi = new SpotifyApi();
    }

    private SpotifyInterface service;

    private SpotifyApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SpotifyInterface.class);
    }

    public SpotifyInterface getService(){
        return service;
    }

}
