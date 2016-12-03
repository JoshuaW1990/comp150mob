package cs.tufts.edu.pocketcritic.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cs.tufts.edu.pocketcritic.R;
import cs.tufts.edu.pocketcritic.model.ArtistSimple;
import cs.tufts.edu.pocketcritic.model.Comments;

/**
 * Created by junwang on 11/19/16.
 */

public class ArtistCommentListFragment extends Fragment {
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//    private List<Comments> commentsList;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Comments, CommentsViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private String searchId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artist_tabcomments, container, false);

        searchId = getArguments().getString("searchInfo");



//        mRecyclerView = (RecyclerView) view.findViewById(R.id.artist_page_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(commentsList);
//        mRecyclerView.setAdapter(mAdapter);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = (RecyclerView) view.findViewById(R.id.artist_page_recycler_view);
        mRecycler.setHasFixedSize(true);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);

        mRecycler.setLayoutManager(mManager);

        Query postsQuery = mDatabase.child("testPost").child(searchId).limitToFirst(10);

        mAdapter = new FirebaseRecyclerAdapter<Comments, CommentsViewHolder>(Comments.class, R.layout.comments_listview,
                CommentsViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final CommentsViewHolder viewHolder, final Comments model, final int position) {
                // Bind Post to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToComment(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }


    public static class CommentsViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mAuthorName;
        TextView mCommentTitle;
        TextView mCommentContent;

        public CommentsViewHolder(View v) {
            super(v);
            mAuthorName = (TextView) v.findViewById(R.id.comment_author_name);
            mCommentTitle = (TextView) v.findViewById(R.id.comment_title);
            mCommentContent = (TextView) v.findViewById(R.id.comment_content);
        }

        public void bindToComment(Comments comment) {
            mAuthorName.setText(comment.author);
            mCommentTitle.setText(comment.title);
            mCommentContent.setText(comment.content);
        }
    }


}
