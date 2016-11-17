package cs.tufts.edu.pocketcritic.support;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import cs.tufts.edu.pocketcritic.volley_support.AppController;

/**
 * Created by junwang on 11/11/16.
 */

public abstract class CustomListAdapter <T> extends BaseAdapter {

    private Context mContext;
    private List<T> mData;
    private int mLayoutId;


    public CustomListAdapter(Context context, List<T> data, int layout_id) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layout_id;
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

        public ViewHolder setImageByRes(int id, int resId) {
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
