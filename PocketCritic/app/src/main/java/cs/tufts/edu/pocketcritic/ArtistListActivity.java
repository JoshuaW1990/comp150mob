package cs.tufts.edu.pocketcritic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cs.tufts.edu.pocketcritic.model.Artist;
import cs.tufts.edu.pocketcritic.support.CustomListAdapter;
import cs.tufts.edu.pocketcritic.volley_support.AppController;

public class ArtistListActivity extends AppCompatActivity {

    private String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        searchQuery = intent.getStringExtra("queryString");

        System.out.println("come to artist list");
        System.out.println(searchQuery);

        ArtistSearch();
    }

    private void ArtistSearch() {
        if (searchQuery.isEmpty()) {
            Toast.makeText(this, "Please input artist name", Toast.LENGTH_SHORT).show();
            return;
        }
        String tag_json_obj = "json_obj_req";
        String url = "https://pocketcritic-server.herokuapp.com/search/Artist/" + searchQuery;
        System.out.println(url);

        final String TAG = "Failure";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("search success!");
                        try {
                            JSONArray jsonArray = response.getJSONArray("artists");
                            if (jsonArray.length() == 0) {
                                Toast.makeText(ArtistListActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                List<Artist> artists = new ArrayList<Artist>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Artist artist = new Artist();
                                    artist.setId(jsonObject.getString("id"));
                                    artist.setImage(jsonObject.getString("image"));
                                    artist.setName(jsonObject.getString("name"));
                                    artist.setPopularity(jsonObject.getInt("popularity"));
                                    artists.add(artist);

                                }
                                display(artists);
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "json exception when reading data");
                            return;
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    private void display(List<Artist> artists) {
        ListView listView = (ListView) findViewById(R.id.artistList_listview);
        listView.setAdapter(new CustomListAdapter<Artist>(this, artists, R.layout.artist_listview) {
            @Override
            public void convert(ViewHolder holder, Artist artist, int position) {
                if (artist.getImage() != null) {
                    holder.setImage(R.id.artistlist_img, artist.getImage());
                } else {
                    holder.setImageByRes(R.id.artistlist_img, R.drawable.placeholder_image);
                }
                System.out.println(artist.getName());
                holder.setText(R.id.artistlist_name, artist.getName());
                holder.setText(R.id.artistlist_popularity, Integer.toString(artist.getPopularity()));
//                ImageView imgView = holder.getView(R.id.artistlist_img);
//                imgView.setClickable(true);
            }
        });

    }

}
