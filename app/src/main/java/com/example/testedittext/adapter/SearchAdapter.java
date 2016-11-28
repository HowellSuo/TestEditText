package com.example.testedittext.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testedittext.R;

public class SearchAdapter extends CustomAdapter<String>{
    public SearchAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewholder = null;
        if (convertView == null) {
            viewholder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.layout_item, viewGroup, false);
            viewholder.item_find_city = (TextView) convertView.findViewById(R.id.item_find_city);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
         /*将list中的数据设置进来*/
        viewholder.item_find_city.setText(getItem(i));

        return convertView;
    }


    static class ViewHolder {
        public TextView item_find_city;
    }
}
