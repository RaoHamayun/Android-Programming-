package com.example.raohamayun.finalproject;

/**
 * Created by rao hamayun on 5/3/2017.
 */
public class ArtistsModel {

    public Integer getArtistsID() {
        return ArtistsID;
    }

    public void setArtistsID(Integer artistsID) {
        ArtistsID = artistsID;
    }

    public long getEventID() {
        return EventID;
    }

    public void setEventID(int eventID) {
        EventID = eventID;
    }

    public String getArtistsName() {
        return ArtistsName;
    }

    public void setArtistsName(String artistsName) {
        ArtistsName = artistsName;
    }

    int ArtistsID;
    int EventID;
    String ArtistsName;
}
