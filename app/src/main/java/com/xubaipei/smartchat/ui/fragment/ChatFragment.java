package com.xubaipei.smartchat.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xubaipei.smartchat.R;
import com.xubaipei.smartchat.Util.Util;
import com.xubaipei.smartchat.ui.activity.ChatActivity;
import com.xubaipei.smartchat.adapter.MessageListAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    Context context = null;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
    //    ArrayAdapter<String> adapter = new ArrayAdapter<String>(container.getContext(),android.R.layout.simple_list_item_checked,getItem());
        MessageListAdapter adapter = new MessageListAdapter(getContext());
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.FOCUSABLES_TOUCH_MODE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(Util.FRIEND_ID,"111");//的打开对应会话，这里后面替换掉真实数据
                startActivity(intent);
            }
        });
        return view;
    }
}
