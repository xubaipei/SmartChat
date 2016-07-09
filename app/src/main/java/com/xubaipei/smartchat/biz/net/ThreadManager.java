package com.xubaipei.smartchat.biz.net;
import java.util.*;
public class ThreadManager {

	private static HashMap hm = new HashMap<String, ConThread>();
	
	public static void addConThead(String threadId,ConThread conThead)
	{
		hm.put(threadId,conThead);
	}	
	public static ConThread getConThead(String threadId)
	{
		return (ConThread)hm.get(threadId);
	}
}
