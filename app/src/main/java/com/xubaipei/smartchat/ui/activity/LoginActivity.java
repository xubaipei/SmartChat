package com.xubaipei.smartchat.ui.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.xubaipei.smartchat.R;
import com.xubaipei.smartchat.common.User;
import com.xubaipei.smartchat.delegate.NotifyChangeDelegate;
import com.xubaipei.smartchat.biz.net.Client;


public class LoginActivity extends Activity implements NotifyChangeDelegate{
    Button loginBtn;
    EditText idEd;
    EditText pwdEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button)findViewById(R.id.loginBt);
        idEd = (EditText)findViewById(R.id.idEd);
        pwdEd = (EditText)findViewById(R.id.pwdEd);

        Client.getInstance(this).setMesDelegate(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isConnect();
            }
        });
    }

    public boolean isConnect(){
        /**
         * 获取登录账号密码*/
        final User user = new User(idEd.getText().toString(),pwdEd.getText().toString().trim());



        /**
         * 连接服务器返回*/
    final  Runnable conRe=  new Runnable(){
            @Override
            public void run() {
                if(Client.getInstance(LoginActivity.this).islogin) {
                    Client.getInstance(LoginActivity.this).user = user;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"成功接入服务器",Toast.LENGTH_LONG).show();
                /*跳转*/
                    finish();
                }else
                {
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                }
            }
        };


        /**
         * 发送到服务端验证
         * 返回true代表连接服务器成功
         * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Client.getInstance(LoginActivity.this).login(user);
                runOnUiThread(conRe);
            }
        }).start();

        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void notifyUiChange() {


    }
}

