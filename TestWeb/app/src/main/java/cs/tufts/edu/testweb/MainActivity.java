package cs.tufts.edu.testweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request.Method;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton;
    private EditText title;
    private EditText done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

        title = (EditText) findViewById(R.id.editText2);
        done = (EditText) findViewById(R.id.editText);


    }

    private void getTask() {
        String tag_json_obj = "json_obj_req";
        String url = "https://pocketcritic-server.herokuapp.com/search/Artist/Linkin%20Park";

        final String TAG = "Failure";

        final TextView mTxtDisplay = (TextView) findViewById(R.id.txtView);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("artists");
                            List<Artist> artists = new ArrayList<Artist>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Artist artist = new Artist();
                                artist.setId(jsonObject.getString("id"));
                                artist.setImage(jsonObject.getString("image"));
                                artist.setName(jsonObject.getString("name"));
                                artist.setPopularity(jsonObject.getInt("popularity"));
                                System.out.println(artist.getName());
                                artists.add(artist);

                            }
                            display(artists);


//                        mTxtDisplay.setText("Response: " + response.toString());
                        } catch (JSONException e) {
                            Log.e(TAG, "json exception when reading data");
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
        ListView listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(new CustomListAdapter<Artist>(this, artists, R.layout.listview) {
            @Override
            public void convert(ViewHolder holder, Artist artist, int position) {
                try {
                    holder.setImage(R.id.list_imageView, artist.getImage());
                } catch(Throwable e) {

                }
                holder.setText(R.id.list_textView, artist.getName());
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button) {
            getTask();
        }
    }
}
