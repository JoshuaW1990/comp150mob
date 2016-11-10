package cs.tufts.edu.pocketcritic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cs.tufts.edu.pocketcritic.R;

/**
 * Created by junwang on 11/3/16.
 */

public class ImageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_image, container, false);
        return view;

    }
}