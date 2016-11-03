package cs.tufts.edu.pocketcritic.fragment;

import cs.tufts.edu.pocketcritic.R;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

/**
 * Created by junwang on 11/3/16.
 */

public class SearchFragment extends Fragment
        implements View.OnClickListener {

    private EditText searchInfo;
    private Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search, container, false);


        // edit views
        searchInfo = (EditText) view.findViewById(R.id.search_searchinfo);

        // click listeners
        searchButton = (Button) view.findViewById(R.id.search_searchButton);
        searchButton.setOnClickListener(this);

        return view;

    }

    public void doSearch() {
        System.out.println("do search here by spotify");
        System.out.println(searchInfo.getText().toString());
    }





    private void onSearchSuccess() {
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.search_searchButton) {
            doSearch();
        }
        else
        {

            System.out.println("error when searching!");
        }
    }
}
