package com.xubaipei.smartchat.biz.net;
import android.content.Context;
import android.util.Log;

import com.xubaipei.smartchat.common.Message;

import java.io.*;
import java.net.*;

public class ConThread extends Thread
{
    private Socket socket;
	Context mContext;

	public ConThread(Socket socket)
	{
		this.socket = socket;
	}
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public void run()
	{
		while(true)
		{
			try {
				ObjectInputStream objInputS = new ObjectInputStream(socket.getInputStream());

				Message messageBack = (Message)objInputS.readObject();

				Client.getInstance(mContext).mesReturn(messageBack);


			} catch (Exception e) {
				Log.e("xubaipei","线程通讯出错");
				return;
			}
		}
	}
}
