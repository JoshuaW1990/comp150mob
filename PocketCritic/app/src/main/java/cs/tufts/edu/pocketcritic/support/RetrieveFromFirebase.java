package cs.tufts.edu.pocketcritic.support;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import cs.tufts.edu.pocketcritic.models.Album;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import cs.tufts.edu.pocketcritic.ArtisitsActivity;

/**
 * Created by alexjackson on 10/16/16.
 */

public class RetrieveFromFirebase extends AsyncTask<DataSnapshot, Void, List<Album>> {

    private ArtisitsActivity artistActivity;
    List<Album> myAlbums;

    public RetrieveFromFirebase(ArtisitsActivity activity)
    {
        this.artistActivity = activity;
        myAlbums = new ArrayList<Album>();
    }

    protected List<Album> doInBackground(DataSnapshot... data) {
        DataSnapshot firebase_albums = data[0];
        for (DataSnapshot child : firebase_albums.getChildren())
        {
            String albumName = child.getKey();
            long albumRating = (long) child.child("0").getValue();
            String albumCoverURL = (String) child.child("1").getValue();
            Album neoAlbum = new Album(albumName, albumRating, albumCoverURL);
            myAlbums.add(neoAlbum);
        }
        return myAlbums;
    }


    protected void onPostExecute(List<Album> myAlbums) {
        artistActivity.setAlbums(myAlbums);
        artistActivity.saveAlbumList(myAlbums);
        System.out.print("Finished on post execute.");
    }
}