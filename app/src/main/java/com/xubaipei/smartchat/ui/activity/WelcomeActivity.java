package com.xubaipei.smartchat.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;
import com.xubaipei.smartchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends Activity {


    @Bind(R.id.root_view)
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);

        initView();

    }

    // initView
    public void initView(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,1f);

        alphaAnimation.setDuration(1000);

        rootView.startAnimation(alphaAnimation);

    }


    @OnClick(R.id.loginBt)
    public void login(View view){
        Toast.makeText(this,"ddd",Toast.LENGTH_LONG).show();
    }



    @OnClick(R.id.registerBt)
    public void register(View view){
        Toast.makeText(this,"ddd",Toast.LENGTH_LONG).show();
    }

}
