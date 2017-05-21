package com.example.raohamayun.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rao hamayun on 4/30/2017.
 */
public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(Context context) {
        super(context, "FinalProjectDB.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table tblEvent(E_ID integer Primary key AUTOINCREMENT,EventName text, Event_Data text, Location text)");
        db.execSQL("Create table tblUser(UserID integer Primary key autoincrement,UserName text Unique,Password text)");
        db.execSQL("Create table tblAudiance(AID integer Primary key AUTOINCREMENT,AudienceName text,AudienceAddress text);");
        db.execSQL("Create table tblTickets(T_ID integer Primary key AUTOINCREMENT,TicketPrice real,AudianceID integer,EventID integer,foreign key (AudianceID) references tblAudiance(AID),foreign key (EventID) references tblEvent(E_ID));");
        db.execSQL("Create table tblArtist(AR_ID integer Primary key AUTOINCREMENT, ArtistName text,EventID integer, Foreign key (EventID) references tblEvent(E_ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if Exist tblUser");
        db.execSQL("Drop Table if Exist tblAudiance");
        db.execSQL("Drop T able if Exist tblTickets");
        db.execSQL("Drop Table if Exist tblEvent");
        db.execSQL("Drop Table if Exist tblArtist");

        onCreate(db);
    }
}
