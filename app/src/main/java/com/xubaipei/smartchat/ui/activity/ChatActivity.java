package com.xubaipei.smartchat.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.xubaipei.smartchat.R;
import com.xubaipei.smartchat.Util.Util;
import com.xubaipei.smartchat.adapter.CommunicationAdapter;
import com.xubaipei.smartchat.common.Message;
import com.xubaipei.smartchat.common.MessageType;
import com.xubaipei.smartchat.delegate.NotifyChangeDelegate;
import com.xubaipei.smartchat.biz.net.Client;

import java.util.Vector;

/**
 * Created by user on 2016/3/16.
 */
public class ChatActivity extends Activity implements NotifyChangeDelegate {

    private ListView mChatListView;
    private CommunicationAdapter  comAdapter;
    private Vector<Message> messageVector;
    EditText editText;
    String friendId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageVector = new Vector<Message>();

        friendId = getIntent().getStringExtra(Util.FRIEND_ID);
        initView();

        /**
         * 注册为消息接受器*/
        Client.getInstance(this).setMesDelegate(this);
    }


    public void initView(){
        mChatListView = (ListView)findViewById(R.id.chatList);
        comAdapter = new CommunicationAdapter(this,friendId);
        editText = (EditText)findViewById(R.id.editText);

        mChatListView.setAdapter(comAdapter);


        ImageView btn_back = (ImageView)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(""))
                    return;

                Message message = new Message("1", MessageType.message_comm_mes,
                        Client.getInstance(ChatActivity.this).user.getUserId(), friendId, "hello", "2016-10-10");
                message.setCon(editText.getText().toString());
                editText.setText("");

                Client.getInstance(ChatActivity.this).send(message);

                Client.getInstance(ChatActivity.this).getmSession(friendId).get(friendId).add(message);
                comAdapter.notifyDataSetChanged();
                mChatListView.smoothScrollToPosition(comAdapter.getCount() - 1);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void notifyUiChange() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                comAdapter.notifyDataSetChanged();
                mChatListView.smoothScrollToPosition(comAdapter.getCount());
            }
        });

    }
}
