package com.xubaipei.smartchat.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xubaipei.smartchat.R;

/**
 * Created by 柏培 on 2016/2/28.
 */
public class MessageListAdapter extends BaseAdapter {
    private Context context = null;
    LayoutInflater mLayoutInflater;


    public MessageListAdapter(Context context){
        super();
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {;

        convertView = mLayoutInflater.inflate(R.layout.chat_list_item,null);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.look);
        TextView userName = (TextView)convertView.findViewById(R.id.userName);
        TextView content = (TextView)convertView.findViewById(R.id.content);
        TextView date = (TextView)convertView.findViewById(R.id.date);

        userName.setText("许柏培");
        content.setText("下来吃饭了");
        date.setText("19：20");
        return convertView;
    }
}
