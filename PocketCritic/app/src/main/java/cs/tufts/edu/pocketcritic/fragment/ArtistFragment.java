package cs.tufts.edu.pocketcritic.fragment;

import cs.tufts.edu.pocketcritic.R;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by junwang on 11/3/16.
 */

public class ArtistFragment extends Fragment
        implements View.OnClickListener {

    Button artistBio;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artist, container, false);

        artistBio = (Button) view.findViewById(R.id.artist_Bio);
        artistBio.setOnClickListener(this);

        return view;
    }





    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.artist_Bio) {
            System.out.println("print artist bio");
        }

    }
}
