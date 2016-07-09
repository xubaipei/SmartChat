package com.xubaipei.smartchat.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.xubaipei.smartchat.ui.fragment.AccountFragment;
import com.xubaipei.smartchat.ui.fragment.ChatFragment;
import com.xubaipei.smartchat.ui.fragment.ContactFragment;
import com.xubaipei.smartchat.R;

public class MainActivity extends AppCompatActivity {

    private Class fragmentArray[] = {ChatFragment.class,ContactFragment.class,AccountFragment.class};
    private int mImageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_message_btn,R.drawable.tab_more_btn};
    private String mTextviewArray[] = {"消息", "好友列表","应用中心"};
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initView();
    }

    private  void initView(){
         layoutInflater = LayoutInflater.from(this);
        // tabhost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.chatList);

        int count = fragmentArray.length;
        for (int i = 0;i < count; i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec,fragmentArray[i],null);
          //  mTabHost.getTabWidget().getChildTabViewAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
    }

    private View getTabView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView)view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
