package com.megaflashgames.budgethelper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.megaflashgames.budgethelper.ui.ViewHolder;
import com.megaflashgames.budgethelper.ui.injector.MFGInjector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanyamihova on 04/05/2015.
 */
abstract public class BaseItemAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater inflater;
    protected List<T> items;

    public BaseItemAdapter(Context mContext, LayoutInflater inflater) {
        this.mContext = mContext;
        this.inflater = inflater;
        setItems(null);
    }

    public BaseItemAdapter(Context mContext, LayoutInflater inflater, List<T> items) {
        this.mContext = mContext;
        this.inflater = inflater;
        setItems(items);
    }

    public void setItems(List<T> items) {
        if(items == null) {
            items = new ArrayList<T>();
        }
        this.items = items;
    }

    public List<T> getItems() {
        if(this.items == null)
            items = new ArrayList<T>();
        return items;
    }

    public void clear() {
        this.getItems().clear();
    }

    @Override
    public int getCount() {
        return this.getItems().size();
    }

    @Override
    public T getItem(int position) {
        return this.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = newViewHolder();

            convertView = MFGInjector.inflate(this, inflater,parent,holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        bindData(getItem(position), holder, position);

        return convertView;
    }

    protected Context getmContext() { return this.mContext; }

    abstract protected void bindData(T item, ViewHolder holder, int position);

    abstract  protected ViewHolder newViewHolder();


}
