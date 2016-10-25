package cs.tufts.edu.pocketcritic.support;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.SparseArray;
import android.view.LayoutInflater;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by junwang on 10/24/16.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private final String TAG = CommonAdapter.class.getSimpleName();
    private List<T> mData;
    private int mItemResId;
    private Context mContext;

    public CommonAdapter(Context context, List<T> data, int itemResId) {
        this.mContext = context;
        this.mData = data;
        this.mItemResId = itemResId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = ViewHolder.newInstance(mContext, view, parent, mItemResId);
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    // Clear the data
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    // Add new data
    public void add(T data){
        mData.add(data);
        notifyDataSetChanged();
    }

    public void addAll(List<T> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Methods need to be rewritten
     * @param holder
     * @param item: the item in the list for now
     * @param position: the index for now
     */
    public abstract void convert (ViewHolder holder, T item, int position);

    public static class ViewHolder {
        private final String TAG = ViewHolder.class.getSimpleName();
        private Context mContext;
        private SparseArray<View> mMaps;
        private View mConvertView;

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

        public ViewHolder setImage(int id, String url, Callback callback) {
            ImageView imageView = getView(id);
            Picasso.with(mContext).load(url).into(imageView, callback);
            return this;
        }

        public ViewHolder setImage(int id, int resId, Callback callback) {
            ImageView imageView = getView(id);
            Picasso.with(mContext).load(resId).into(imageView, callback);
            return this;
        }

        public ViewHolder setImage(int id, File file, Callback callback) {
            ImageView imageView = getView(id);
            Picasso.with(mContext).load(file).into(imageView, callback);
            return this;
        }

        public View getConvertView() {
            return mConvertView;
        }

    }




}
