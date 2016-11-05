package cs.tufts.edu.pocketcritic.support;

/**
 * Created by joshuaw on 11/4/16.
 */

import cs.tufts.edu.pocketcritic.model.Album;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SpotifyInterfaceAlbum {
    @GET("/v1/search?")
    Call<Album> getResult(@Query("q") String searchName,
                           @Query("type") String searchType);

    @GET("/v1/search?")
    Observable<Album> getSpotifyResult(@Query("q") String searchName,
                                        @Query("type") String searchType);
}