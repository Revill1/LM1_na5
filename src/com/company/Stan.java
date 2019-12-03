package com.company;

import java.util.HashMap;

public class Stan
{
    String nazwa;
    HashMap<String,String> mapaPrzejsc = new HashMap<String,String> ();

    public Stan(String nazwa, HashMap<String,String> mapaPrzejsc)
    {
        this.nazwa = nazwa;
        this.mapaPrzejsc = mapaPrzejsc;
    }

    public String getNazwa()
    {
        return nazwa;
    }

    public void setNazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }

    public void setMapaPrzejsc(HashMap<String, String> mapaPrzejsc)
    {
        this.mapaPrzejsc = mapaPrzejsc;
    }

    public HashMap<String, String> getMapaPrzejsc()
    {
        return mapaPrzejsc;
    }
}
