package com.xtkj.paopaoxiche.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomAdapter<T> extends BaseAdapter {

    public final int SELECT_NULL = -1;

    protected int selectPosition = SELECT_NULL;

    protected LayoutView mLayoutView;
    protected List<T> mObjects = new ArrayList<T>();

    public CustomAdapter(List<T> objects) {
        if (objects != null) {
            mObjects.addAll(objects);
        }
    }

    public CustomAdapter(T[] objects) {
        addNewObjects(objects);
    }

    public interface LayoutView {
        public <T> View setView(int position, View convertView, ViewGroup parent);
    }

    public void setLayoutView(LayoutView layoutView) {
        mLayoutView = layoutView;
    }

    public List<T> getAdapterData() {
        return mObjects;
    }

    public void setSelectPosition(int position) {
        selectPosition = position;
        notifyDataSetChanged();
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    /**
     * 更新数据，会删除原有的数据并更新view
     */
    public void updateDataAndNotifyDataChanged(List<T> objects) {
        resetDataAndPosition();
        addNewObjects(objects);
        notifyDataSetChanged();
    }

    /**
     * 更新数据，会删除原有的数据并更新view
     */
    public void updateDataAndNotifyDataChanged(T[] objects) {
        resetDataAndPosition();
        addNewObjects(objects);
        notifyDataSetChanged();
    }

    /**
     * 更新数据，会删除原有的数据
     */
    public void updateData(T[] objects) {
        resetDataAndPosition();
        addNewObjects(objects);
    }

    /**
     * 更新数据，会删除原有的数据并更新view
     */
    public void updateData(List<T> objects) {
        resetDataAndPosition();
        addNewObjects(objects);
    }

    /**
     * 在数据源末尾添加数据，且不调用{@code notifyDataSetChanged()}方法
     */
    public void addDatas(List<T> objects) {
        addNewObjects(objects);
    }


    /**
     * 在数据源末尾添加数据，且不调用{@code notifyDataSetChanged()}方法
     */
    public void addDatas(T[] objects) {
        addNewObjects(objects);
    }

    /**
     * 在数据源末尾添加数据，且不调用{@code notifyDataSetChanged()}方法
     */
    public void addData(T object) {
        if (object != null) {
            mObjects.add(object);
        }
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public T getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mLayoutView != null) {
            return mLayoutView.setView(position, convertView, parent);
        } else {
            return null;
        }
    }

    private void resetDataAndPosition() {
        selectPosition = SELECT_NULL;
        mObjects.clear();
    }

    private void addNewObjects(T[] objects) {
        if (objects != null) {
            mObjects.addAll(Arrays.asList(objects));
        }
    }

    private void addNewObjects(List<T> objects) {
        if (objects != null) {
            mObjects.addAll(objects);
        }
    }

}
