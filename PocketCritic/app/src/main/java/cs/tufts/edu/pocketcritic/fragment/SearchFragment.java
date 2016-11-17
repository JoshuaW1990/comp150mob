package cs.tufts.edu.pocketcritic.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request.Method;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cs.tufts.edu.pocketcritic.ArtistListActivity;
import cs.tufts.edu.pocketcritic.R;
import cs.tufts.edu.pocketcritic.model.Artist;
import cs.tufts.edu.pocketcritic.volley_support.AppController;


/**
 * Created by junwang on 11/10/16.
 */

public class SearchFragment extends Fragment implements View.OnClickListener {

    private EditText searchInfo;
    private Button searchArtist;
    private Button searchAlbum;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        // edit views
        searchInfo = (EditText) view.findViewById(R.id.search_searchinfo);


        // click listeners
        searchArtist = (Button) view.findViewById(R.id.search_searchArtist);
        searchArtist.setOnClickListener(this);
        searchAlbum = (Button) view.findViewById(R.id.search_searchAlbum);
        searchAlbum.setOnClickListener(this);



        return view;
    }

    private String generateQueryString(String str) {
        return str.replaceAll("\\s+","+");
    }


    private void ArtistSearch() {
        String searchQuery = generateQueryString(searchInfo.getText().toString());
        if (searchQuery.isEmpty()) {
            Toast.makeText(getActivity(), "Please input artist name", Toast.LENGTH_SHORT).show();
            return;
        }
        String tag_json_obj = "json_obj_req";
        String url = "https://pocketcritic-server.herokuapp.com/search/Artist/" + searchQuery;
        System.out.println(url);

        final String TAG = "Failure";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("search success!");
                        try {
                            JSONArray jsonArray = response.getJSONArray("artists");
                            if (jsonArray.length() == 0) {
                                Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                System.out.println(jsonArray.getJSONObject(0).getString("name"));
                                onSearchArtistSuccess();
                            }
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

    private void AlbumSearch() {
        String searchQuery = generateQueryString(searchInfo.getText().toString());
        if (searchQuery.isEmpty()) {
            Toast.makeText(getActivity(), "Please input search information", Toast.LENGTH_SHORT).show();
            return;
        }
        String tag_json_obj = "json_obj_req";
        String url = "https://pocketcritic-server.herokuapp.com/search/Album/" + searchQuery;

        final String TAG = "Failure";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("search success!");
                        try {
                            JSONArray jsonArray = response.getJSONArray("albums");
                            if (jsonArray.length() == 0) {
                                Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                System.out.println(jsonArray.getJSONObject(0).getString("name"));
                                onSearchAlbumSuccess();
                            }
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


    private void onSearchArtistSuccess() {
        Intent intent = new Intent(getActivity(), ArtistListActivity.class);
        intent.putExtra("queryString", generateQueryString(searchInfo.getText().toString()));
        startActivity(intent);
    }

    private void onSearchAlbumSuccess() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.search_searchArtist) {
            ArtistSearch();
        }
        else if (id == R.id.search_searchAlbum)
        {
            AlbumSearch();
            System.out.println("error when searching!");
        }
    }
}
