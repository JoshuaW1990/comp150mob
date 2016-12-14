package cs.tufts.edu.pocketcritic.support;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junwang on 11/16/16.
 */

public class SpotifyAlbumApi {

    public static final String BASE_URL = "https://api.spotify.com";


    /**
     * 获取PhoneApi实例
     * @return
     */
    public static SpotifyAlbumApi getApi(){
        return SpotifyAlbumApi.ApiHolder.spotifyAlbumApi;
    }

    static class ApiHolder{
        private static SpotifyAlbumApi spotifyAlbumApi = new SpotifyAlbumApi();
    }

    private SpotifyAlbumInterface service;

    private SpotifyAlbumApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SpotifyAlbumInterface.class);
    }

    public SpotifyAlbumInterface getService(){
        return service;
    }


}
