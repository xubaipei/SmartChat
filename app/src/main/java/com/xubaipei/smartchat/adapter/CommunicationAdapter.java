package com.xubaipei.smartchat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xubaipei.smartchat.R;
import com.xubaipei.smartchat.common.Message;
import com.xubaipei.smartchat.biz.net.Client;

/**
 * Created by user on 2016/3/26.
 */
public class CommunicationAdapter extends BaseAdapter {

    LayoutInflater mLayoutInflater;
    Context mContext;
    String cuurentId;

    public CommunicationAdapter(Context context,String id){
        super();
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        cuurentId = id;
    }
    @Override
    public int getCount() {
        return Client.getInstance(mContext).getmSession(cuurentId).get(cuurentId).size();
    }

    @Override
    public Object getItem(int position) {
        return Client.getInstance(mContext).getmSession(cuurentId).get(cuurentId).get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = Client.getInstance(mContext).getmSession(cuurentId).get(cuurentId).get(position);
        ViewHolder vhget;
        ViewHolder vhsend;
        if (message.getSender() !=  Client.getInstance(mContext).user.getUserId()) {    //  如果消息的发送者不是自己就显示在左侧
            if (convertView == null){
                convertView = mLayoutInflater.inflate(R.layout.chat_con_item_other,null);
                vhget = new ViewHolder();
                vhget.chatCon = (TextView)convertView.findViewById(R.id.content);
                convertView.setTag(vhget);
            }
            else {
                vhget = (ViewHolder)convertView.getTag();
            }
            vhget.chatCon.setText(message.getCon());
        }else {

            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.chat_con_item_me, null);
                vhsend = new ViewHolder();
                vhsend.chatCon = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(vhsend);
            } else {
                vhsend = (ViewHolder) convertView.getTag();
            }
            vhsend.chatCon.setText(message.getCon());
        }


        return convertView;
    }

    static class ViewHolder{
        TextView chatCon;
    }
}
