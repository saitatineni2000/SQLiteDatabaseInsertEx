package com.example.saisandeep.sqlitedatabaseinsertex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.net.ContentHandler;

/**
 * Created by saisandeep on 3/23/2015.
 */
public class SandeepDataBaseAdapter {

    SandeepHelper sandeepHelper;
    public SandeepDataBaseAdapter(Context context){

        sandeepHelper=new SandeepHelper(context);
    }
    public long insertData(String name,String password)
    {

        SQLiteDatabase sqLiteDatabase=sandeepHelper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(SandeepHelper.NAME, name);
        cv.put(SandeepHelper.PASSWORD,password);
        long id=sqLiteDatabase.insert(SandeepHelper.TABLE_NAME,null,cv);

        return id;
    }

    public String getAllData()
    {
        //select id,name,password  from deeputable;
        SQLiteDatabase sqLiteDatabase=sandeepHelper.getWritableDatabase();

        String[] col={SandeepHelper.UID,SandeepHelper.NAME,SandeepHelper.PASSWORD};
        Cursor cursor=sqLiteDatabase.query(SandeepHelper.TABLE_NAME, col, null, null, null, null, null);

        StringBuffer sb=new StringBuffer();
        while(cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(SandeepHelper.UID);
            int index2=cursor.getColumnIndex(SandeepHelper.NAME);
            int index3=cursor.getColumnIndex(SandeepHelper.PASSWORD);

            int cid=cursor.getInt(index1);
            String username=cursor.getString(index2);
            String password=cursor.getString(index3);

            sb.append(cid+" "+username+" "+password+"\n");

        }

        return sb.toString();
    }

    public String getData(String name)
    {
        //select name,password from deeputable where name="barca";

        SQLiteDatabase sqLiteDatabase=sandeepHelper.getWritableDatabase();

        String[] col={SandeepHelper.NAME,SandeepHelper.PASSWORD};
        Cursor cursor=sqLiteDatabase.query(SandeepHelper.TABLE_NAME, col, SandeepHelper.NAME+" ='"+name+"'", null, null, null, null,null);

        //you can do querying the below way aswell using the ? fro selection args
        /*
        String[] col={SandeepHelper.UID};
        Cursor cursor=sqLiteDatabase.query(SandeepHelper.TABLE_NAME,col,SandeepHelper.NAME+" =? AND "+SandeepHelper.PASSWORD+" =?",selectionArgzz,null,null,null,null);
         String[] selectionArgzz={name,password};

*/
        StringBuffer sb1=new StringBuffer();
        while(cursor.moveToNext())
        {
           // int index1=cursor.getColumnIndex(SandeepHelper.UID);
            int index2=cursor.getColumnIndex(SandeepHelper.NAME);
            int index3=cursor.getColumnIndex(SandeepHelper.PASSWORD);

            //int cid=cursor.getInt(index1);
            String username=cursor.getString(index2);
            String password=cursor.getString(index3);

            sb1.append(username+" "+password+"\n");

        }
        return sb1.toString();
    }

    public int UpdateName(String oldName, String newName) {
        SQLiteDatabase sqLiteDatabase = sandeepHelper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(SandeepHelper.NAME,newName);
        String[] whereArgs={oldName};
        int count=sqLiteDatabase.update(SandeepHelper.TABLE_NAME,cv,SandeepHelper.NAME +" =?",whereArgs);

        return count;
    }

    public int deleteRow(String deleteName)
    {
        SQLiteDatabase sqLiteDatabase = sandeepHelper.getWritableDatabase();

        String[] whereArgs={deleteName};
        int count=sqLiteDatabase.delete(SandeepHelper.TABLE_NAME,SandeepHelper.NAME +" =?",whereArgs);

        return count;
    }

    static class SandeepHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "SandeepDatabase";
        private static final String TABLE_NAME = "DeepuTable";
        private static final int DATABASE_VERSION = 2;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        Context context;

        public SandeepHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Toast.makeText(context, "constructor was called", Toast.LENGTH_SHORT).show();


            //"CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+PASSWORD+" VARCHAR(255));"
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Toast.makeText(context, "OnCreate was called", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                Toast.makeText(context, "OnUpgrade was called", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}