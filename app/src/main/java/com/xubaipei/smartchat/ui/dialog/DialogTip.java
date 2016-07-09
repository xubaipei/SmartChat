package com.xubaipei.smartchat.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xubaipei.smartchat.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/3/5.
 */
public class DialogTip extends Dialog {

    private LayoutInflater inflater;
    private Context mContext;
    private WindowManager.LayoutParams lp;

    public DialogTip(Context context,String alertMsg) {
        super(context, R.style.AppTheme);
        this.mContext = context;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view = inflater.inflate(R.layout.dialog_view, null);
        TextView textView =(TextView)view.findViewById(R.id.tip);
        textView.setText(alertMsg);
        setContentView(view);
        // 设置window属性
        lp = getWindow().getAttributes();
        lp.alpha = 0f;
        lp.gravity = Gravity.CENTER;
        lp.dimAmount = 0; // 去背景遮盖
        getWindow().setAttributes(lp);

    }
    @Override
    public void show() {
        super.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                DialogTip.super.dismiss();
            }
        },1000);
    }
}
