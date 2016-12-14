package cs.tufts.edu.pocketcritic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anton46.collectionitempicker.CollectionPicker;
import com.anton46.collectionitempicker.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs.tufts.edu.pocketcritic.R;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.model.Genre;


/**
 * Created by junwang on 11/22/16.
 */

public class ArtistGenreListFragment extends Fragment {
    private DatabaseReference mDatabase;
    private String searchId;

    private View rootView;

    private TagFlowLayout mFlowLayout;
    private TagAdapter<String> mAdapter ;

    private static final String TAG = "Artist Genre Info";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.artist_tabgenrelist, container, false);

        searchId = getArguments().getString("searchInfo");

        mDatabase = FirebaseDatabase.getInstance().getReference("artists").child(searchId).child("genres");

        mFlowLayout = (TagFlowLayout) rootView.findViewById(R.id.artist_tag_layout);





        return rootView;
    }






}
