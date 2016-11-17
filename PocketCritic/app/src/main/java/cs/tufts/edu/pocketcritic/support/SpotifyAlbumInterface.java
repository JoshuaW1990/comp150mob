package cs.tufts.edu.pocketcritic.support;

import cs.tufts.edu.pocketcritic.model.SingleAlbum;
import cs.tufts.edu.pocketcritic.model.SingleArtist;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by junwang on 11/16/16.
 */

public interface SpotifyAlbumInterface {
    @GET("/v1/albums/{id}")
    Call<SingleAlbum> getResult(@Path("id") String searchId);

    @GET("/v1/albums/{id}")
    Observable<SingleAlbum> getSpotifyResult(@Path("id") String searchId);
}
