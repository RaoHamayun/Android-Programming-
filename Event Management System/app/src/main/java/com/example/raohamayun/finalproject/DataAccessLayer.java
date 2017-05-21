package com.example.raohamayun.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rao hamayun on 4/30/2017.
 */
public class DataAccessLayer {

    private DBhelper dBhelper;
    SQLiteDatabase db;

    public DataAccessLayer(Context context)
    {
        dBhelper = new DBhelper(context);
    }
    public void OpenConnection()
    {
        db = dBhelper.getWritableDatabase();
    }
    public long CreateUser(UserModel userModel)
    {
        ContentValues values = new ContentValues();

        //values.put("UserID",userModel.getID());
        values.put("UserName",userModel.getUserName());
        values.put("Password",userModel.getPassword());

        long Result = db.insert("tblUser",null,values);

        return Result;
    }

    public int GetUser(UserModel userModel)
    {
       Cursor cursor = db.query("tblUser",null,"UserName = ?",new String[]{""+userModel.getUserName()},null,null,null,null);
        cursor.moveToFirst();

        int Result = 0;

        String UserName = cursor.getString(cursor.getColumnIndex("UserName"));
        String password = cursor.getString(cursor.getColumnIndex("Password"));

       if(UserName.equals(userModel.getUserName()) && password.equals(userModel.getPassword()) )
       {
            Result = 1;
       }
        return Result;
    }
    public void CloseConnection()
    {
        db.close();
    }

    public List<EventModel> GetEvents ()
    {
        Cursor cursor = db.query("tblEvent",null,null,null,null,null,null);
        cursor.moveToFirst();
        List<EventModel> eventModels = new ArrayList<EventModel>();

        while (cursor.moveToNext())
        {
            EventModel eventModel = new EventModel();
            eventModel.setID(cursor.getInt(cursor.getColumnIndex("E_ID")));
            eventModel.setEventName(cursor.getString(cursor.getColumnIndex("EventName")));
            eventModel.setEventVenue(cursor.getString(cursor.getColumnIndex("Location")));
            eventModel.setEventDate(cursor.getString(cursor.getColumnIndex("Event_Data")));
            eventModels.add(eventModel);
        }
        return eventModels;
    }
    public long CreateEvent(EventModel eventModel)
    {

        ContentValues values = new ContentValues();

        values.put("EventName",eventModel.getEventName());
        values.put("Location",eventModel.getEventVenue());
        values.put("Event_Data",eventModel.getEventDate());

        long Result = db.insert("tblEvent",null,values);

        return Result;
    }
    public void DeleteAll(String tableName)
    {
        db.delete(tableName,null,null);
    }
    public int DeleteItem(String ID,Context context)
    {
        try
        {
            int Result = db.delete("tblEvent","E_ID =?",new String[]{ID});
            return Result;
        }
        catch (Exception ex)
        {
            Toast.makeText(context,ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
        return 0;
    }
    public long AddArtist(ArtistsModel artistsModel)
    {
        ContentValues values = new ContentValues();

        values.put("ArtistName",artistsModel.getArtistsName());
        values.put("EventID",artistsModel.getEventID());

        long Result = db.insert("tblArtist",null,values);

        return Result;
    }
    public List<ArtistsModel> GetListByID(int ID)
    {
        Cursor cursor = db.query("tblArtist",null,"EventID = ?",new String[]{String.valueOf(ID)},null,null,null);

        List<ArtistsModel> artistsModels = new ArrayList<ArtistsModel>();
        cursor.moveToFirst();
        do
        {
            ArtistsModel artistsModel = new ArtistsModel();
            artistsModel.setArtistsName(cursor.getString(cursor.getColumnIndex("ArtistName")));
            artistsModels.add(artistsModel);
        }while(cursor.moveToNext());
        return artistsModels;
    }
    public int UpdateEvent(EventModel eventModel,String ID)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventName",eventModel.getEventName());
        contentValues.put("Location",eventModel.getEventVenue());
        contentValues.put("Event_Data",eventModel.getEventDate());


        int Result = db.update("tblEvent",contentValues,"E_ID = ?",new String[]{String.valueOf(ID)});
        return Result;
    }
    public EventModel GetEventByID(String ID)
    {
        Cursor cursor = db.query("tblEvent",null,"E_ID = ?",new String[]{String.valueOf(ID)},null,null,null);
        cursor.moveToFirst();
        EventModel eventModel = new EventModel();
        do
        {
            eventModel.setEventName(cursor.getString(cursor.getColumnIndex("EventName")));
            eventModel.setEventVenue(cursor.getString(cursor.getColumnIndex("Location")));
            eventModel.setEventDate(cursor.getString(cursor.getColumnIndex("Event_Data")));
        }while (cursor.moveToNext());

        return eventModel;
    }
}
