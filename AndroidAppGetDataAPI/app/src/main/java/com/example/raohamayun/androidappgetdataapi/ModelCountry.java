package com.example.raohamayun.androidappgetdataapi;

/**
 * Created by rao hamayun on 5/14/2017.
 */
public class ModelCountry {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long population) {
        Population = population;
    }

    int ID;
    String CountryName;
    long Population;

}
