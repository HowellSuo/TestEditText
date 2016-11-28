package com.example.testedittext.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected ArrayList<T> mData;
    protected LayoutInflater mInflater;

    public CustomAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    /**
     * @param data 设置一组数据
     */
    public void setData(List<T> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * @param data 添加一组数据
     */
    public void addData(List<T> data) {
        if (data != null) {

            /*add是将传入的参数作为当前List中的一个Item存储，即使你传入一个List也只会另当前的List增加1个元素
            addAll是传入一个List，将此List中的所有元素加入到当前List中，也就是当前List会增加的元素个数为传入的List的大小*/

            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 清空全部数据
     */
    public void clearAll() {
        mData.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int i) {
        if (i < mData.size() && i >= 0) {
            return mData.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
