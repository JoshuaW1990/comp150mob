package cs.tufts.edu.pocketcritic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cs.tufts.edu.pocketcritic.R;

/**
 * Created by junwang on 11/3/16.
 */

public class RecommendFragment extends Fragment
        implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend, container, false);
        return view;

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
    }
}
