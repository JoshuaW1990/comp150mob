package cs.tufts.edu.pocketcritic.support;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junwang on 11/4/16.
 */

public class SpotifyArtistApi {
    public static final String BASE_URL = "https://api.spotify.com";


    /**
     * 获取PhoneApi实例
     * @return
     */
    public static SpotifyArtistApi getApi(){
        return SpotifyArtistApi.ApiHolder.spotifyArtistApi;
    }

    static class ApiHolder{
        private static SpotifyArtistApi spotifyArtistApi = new SpotifyArtistApi();
    }

    private SpotifyArtistInterface service;

    private SpotifyArtistApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SpotifyArtistInterface.class);
    }

    public SpotifyArtistInterface getService(){
        return service;
    }

}
