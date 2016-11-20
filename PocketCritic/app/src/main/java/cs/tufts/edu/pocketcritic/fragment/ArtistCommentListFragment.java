package cs.tufts.edu.pocketcritic.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.tufts.edu.pocketcritic.R;

/**
 * Created by junwang on 11/19/16.
 */

public class ArtistCommentListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artist_tabcomments, container, false);
        return view;
    }
}
