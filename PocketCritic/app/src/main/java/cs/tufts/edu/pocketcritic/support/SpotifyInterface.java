package cs.tufts.edu.pocketcritic.support;

/**
 * Created by junwang on 11/3/16.
 */

import cs.tufts.edu.pocketcritic.model.Artist;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SpotifyInterface {
    @GET("/v1/search?")
    Call<Artist> getResult(@Query("q") String searchName,
                           @Query("type") String searchType);

    @GET("/v1/search?")
    Observable<Artist> getSpotifyResult(@Query("q") String searchName,
                                        @Query("type") String searchType);
}
