package com.xubaipei.smartchat.biz.net;
import android.content.Context;
import android.util.Log;

import com.xubaipei.smartchat.common.Message;
import com.xubaipei.smartchat.common.User;

import java.net.*;
import java.io.*;

public class Connection 
{

	public Socket socket;
	Context mContext;
	public Connection(Context context)
	{
		mContext = context;
	}
	public boolean sendMessage(User user)
	{
		boolean isSend=false;
		try {
			socket = new Socket(Common.SERVE,Common.PORT);

			ObjectOutputStream objOutputS = new ObjectOutputStream(socket.getOutputStream());

			objOutputS.writeObject(user);

            ObjectInputStream objInputS = new ObjectInputStream(socket.getInputStream());

			Message messageBack=(Message)objInputS.readObject();


			ConThread conThead = new ConThread(socket);
			conThead.start();
			ThreadManager.addConThead(user.getUserId(), conThead);

			Client.getInstance(mContext).islogin = true;

			return true;
		} catch (Exception e) {
			Log.e("xubaipei",e.toString());
		}
		return isSend;
	}
}