package cs.tufts.edu.testweb;

import android.app.Activity;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by junwang on 11/11/16.
 */

public abstract class CustomListAdapter<T> extends BaseAdapter {
    private Context mContext;
//    private LayoutInflater mInflater;
    private List<T> mData;
    private int mLayoutId;
//    private int mImageviewId;
//    private int mTextViewId;
//    private int mImageURL;


    public CustomListAdapter(Context context, List<T> data, int layout_id) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layout_id;
//        this.mImageviewId = imageViewId;
//        this.mTextViewId = textViewId;
//        this.mImageURL = imageURL;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int location) {
        return mData.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = ViewHolder.newInstance(mContext, view, parent, mLayoutId);
        convert(holder, getItem(position), position);
        return holder.getConvertView();


//        if (mInflater == null)
//            mInflater = (LayoutInflater) mActivity
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null)
//            convertView = mInflater.inflate(mLayoutId, null);
//
//
//        TextView title = (TextView) convertView.findViewById(R.id.title);
//
//        // getting movie data for the row
//        T m = mData.get(position);
//
//        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl(), mImageLoader);
//
//        // title
//        title.setText(m.getTitle());
//
//        // rating
//        rating.setText("Rating: " + String.valueOf(m.getRating()));
//
//        // genre
//        String genreStr = "";
//        for (String str : m.getGenre()) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
//        genre.setText(genreStr);
//
//        // release year
//        year.setText(String.valueOf(m.getYear()));
//
//        return convertView;
    }

    /**
     * Methods need to be rewritten
     * @param holder: holds stuff
     * @param item: the item in the list for now
     * @param position: the index for now
     */
    public abstract void convert (ViewHolder holder, T item, int position);

    public static class ViewHolder {
        private final String TAG = ViewHolder.class.getSimpleName();
        private Context mContext;
        private SparseArray<View> mMaps;
        private View mConvertView;
        ImageLoader mImageLoader = AppController.getInstance().getImageLoader();

        private ViewHolder(Context context, View convertView, ViewGroup parent, int itemResId) {
            mMaps = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(itemResId, parent, false);
            mConvertView.setTag(this);
            convertView = mConvertView;
            this.mContext = context;
        }

        public static ViewHolder newInstance(Context context, View convertView, ViewGroup parent, int itemResId) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder(context, convertView, parent, itemResId);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return holder;
        }

        public <T extends View> T getView(int id) {
            View view = mMaps.get(id);
            if (view == null) {
                view = mConvertView.findViewById(id);
                mMaps.put(id, view);
            }
            return (T)view;
        }

        public ViewHolder setText(int id, String txt) {
            TextView textview = getView(id);
            textview.setText(txt);
            return this;
        }

        public ViewHolder setImage(int id, String url) {
            if (mImageLoader == null)
                mImageLoader = AppController.getInstance().getImageLoader();
            NetworkImageView imageView = (NetworkImageView) mConvertView
                    .findViewById(id);
            imageView.setImageUrl(url, mImageLoader);
            return this;
        }

        public ViewHolder setImage(int id, int resId) {
            if (mImageLoader == null)
                mImageLoader = AppController.getInstance().getImageLoader();
            NetworkImageView imageView = (NetworkImageView) mConvertView
                    .findViewById(id);
            imageView.setImageResource(resId);
            return this;
        }


        public View getConvertView() {
            return mConvertView;
        }

    }


}
