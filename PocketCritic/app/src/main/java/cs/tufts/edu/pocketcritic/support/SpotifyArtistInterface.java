package cs.tufts.edu.pocketcritic.support;

import cs.tufts.edu.pocketcritic.model.SingleArtist;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by junwang on 11/4/16.
 */

public interface SpotifyArtistInterface {
    @GET("/v1/artists/{id}")
    Call<SingleArtist> getResult(@Path("id") String searchId);

    @GET("/v1/artists/{id}")
    Observable<SingleArtist> getSpotifyResult(@Path("id") String searchId);
}
