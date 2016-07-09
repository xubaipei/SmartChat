package com.xubaipei.smartchat.biz.db;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xubaipei.smartchat.common.Message;

import java.io.File;
import java.util.Vector;

/**
 * Created by 柏培 on 2016/3/7.
 */
public class DBManager{
    private static DBManager dbManager;

    /*数据库信息*/
    private static final String DB_NAME = "chat.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDataBase;

    private String mesType;
    private String sender;
    private String getter;
    private String con;
    private String sendTime;

    /*会话列表*/
    public static final String TABLE_NAME = "message";
    private static final String SQL_CREATE_TABLE = "create table if not exists " + TABLE_NAME
            + " ( _ID integer primary key, "
            + "   _MESTYPE varchar(30),"
            + "   _SENDER varchar(30),"
            + "   _GETTER varchar(30),"
            + "   _CONTENT varchar(30),"
            + "   _SENDTIME varchar(30)"
            + "  )";
    private static final String SQL_INSERT_MERCHANT = "insert or replace into " + TABLE_NAME + "([_ID],[_MESTYPE],[_SENDER],[_GETTER],[_CONTENT],[_SENDTIME]) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_MERCHANT = "delete from "+ TABLE_NAME +"where id=?";
    private static final String SQL_SELECT_MERCHANT = "select * from "+TABLE_NAME+"";

    public DBManager(Context context){
        String FullPath;
        /*初始化数据库*/
        File f=context.getDatabasePath(DB_NAME).getParentFile();
        if(f.exists()==false)f.mkdirs();//注意是mkdirs()有个s 这样可以创建多重目录。
        FullPath=f.getPath()+"/"+DB_NAME;
        mDataBase=SQLiteDatabase.openOrCreateDatabase(FullPath,null);
        mDataBase.execSQL(SQL_CREATE_TABLE);
    }

    public void closeDB(){
        mDataBase.close();
        dbManager = null;
    }

    public static DBManager getDbManager() {
        return dbManager;
    }

    public boolean insertMerchat(Message message){
            mDataBase.execSQL(SQL_INSERT_MERCHANT,new Object[]{
                    message.getId(),
                    message.getMesType(),
                    message.getSender(),
                    message.getGetter(),
                    message.getCon(),
                    message.getSendTime()
            });
        return true;
    }

    public boolean updateMessage(Message[] messagesList){
        mDataBase.execSQL(SQL_INSERT_MERCHANT, messagesList);
        return true;
    }

    public Vector<Message> findMerchat(){
        Vector<Message> messagesList = new Vector<Message>();
        Message message;
        Cursor cursor = mDataBase.rawQuery(SQL_SELECT_MERCHANT, null);
        while (cursor.moveToNext()) {
            message = new Message(
                    cursor.getString(0), cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5));
            messagesList.add(message);
        }
        cursor.close();
        return messagesList;
    }

    public boolean deleteMerchat(Message message){
        mDataBase.execSQL(SQL_DELETE_MERCHANT,new Object[]{message.getId()});
        return true;
    }

}
