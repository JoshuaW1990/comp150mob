package cs.tufts.edu.pocketcritic.support;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cs.tufts.edu.pocketcritic.fragment.ArtistAlbumListFragment;
import cs.tufts.edu.pocketcritic.fragment.ArtistCommentListFragment;


/**
 * Created by junwang on 11/19/16.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ArtistAlbumListFragment tab1 = new ArtistAlbumListFragment();
                return tab1;
            case 1:
                ArtistCommentListFragment tab2 = new ArtistCommentListFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
