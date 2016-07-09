package com.xubaipei.smartchat.biz.net;

import android.content.Context;
import android.util.Log;

import com.xubaipei.smartchat.common.Message;
import com.xubaipei.smartchat.common.MessageType;
import com.xubaipei.smartchat.common.User;
import com.xubaipei.smartchat.delegate.NotifyChangeDelegate;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;


public class Client{

	static Client client;
	Context mContext;
	public User user;
	public  boolean islogin = false;
	Connection connection;
	NotifyChangeDelegate notifyChangeDelegate;
	Vector<Message> messagesReturn;

	public HashMap<String, Vector<Message>> getmSession(String friendId) {
		if (mSession.get(friendId)==null)
		mSession.put(friendId,new Vector<Message>());
		return mSession;
	}

	HashMap<String,Vector<Message>> mSession;	// 会话friendId - messagelist

	public Client setMesDelegate(NotifyChangeDelegate notifyChangeDelegate) {
		this.notifyChangeDelegate = notifyChangeDelegate;
		return this;
	}

	public static Client getInstance(Context context){

		if (client == null)
			client = new Client(context);

		return client;
	}

	public Client(Context context){
		mContext = context;
		mSession = new HashMap<String,Vector<Message>>();
	}

	public void send(Message message){
		Socket socket = ThreadManager.getConThead(user.getUserId()).getSocket();
		try {
			ObjectOutputStream os =new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(message);
		} catch (Exception e) {
			Log.e("xubapei","发送失败");
		}
	}

	public void login(User user){
		this.user = user;
		connection = new Connection(mContext);
		islogin = connection.sendMessage(user);
		return ;
	}

	public void nofifyUi() {
		/*通知界面更新*/
		if (notifyChangeDelegate != null)
			notifyChangeDelegate.notifyUiChange();
	}

	public void mesReturn(Message message){	//处理服务器返回的消息
		switch (message.getMesType()){
			case MessageType.message_comm_mes:
				addMessage(message);
				break;
			case MessageType.message_login_fail:
				break;
			case MessageType.message_get_onLineFriend:
				break;
			case MessageType.message_ret_onLineFriend:
				break;
		}
		nofifyUi();
	}

	public void addMessage(Message message){
		/*聊天数据增加*/
		if(mSession.get(message.getSender()) == null){
				/*本地没有会话*/
			mSession.put(message.getSender(),new Vector<Message>());
			mSession.get(message.getSender()).add(message);
		}else {
			mSession.get(message.getSender()).add(message);
		}
	}
}
