package cs.tufts.edu.pocketcritic.support;

/**
 * Created by junwang on 10/12/16.
 * http://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView theImage;

    public DownloadImageTask(ImageView view) {
        this.theImage = view;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap bitImage = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bitImage = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitImage;
    }

    protected void onPostExecute(Bitmap result) {
        theImage.setImageBitmap(result);
    }
}
