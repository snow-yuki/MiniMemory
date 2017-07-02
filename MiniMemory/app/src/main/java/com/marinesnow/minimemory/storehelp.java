package com.marinesnow.minimemory;

/**
 * Created by mei on 2017/7/2.
 */

import android.content.SharedPreferences;
import android.content.Context;

public class storehelp
{
    public static final String MAIN_SET = "main";

    private SharedPreferences sp;
    private SharedPreferences.Editor se;

    public storehelp(Context cp,String name){
        sp = cp.getSharedPreferences(name,0);
        se = sp.edit();
    }

    public storehelp(Context c){
        sp = c.getSharedPreferences(MAIN_SET,0);
        se = sp.edit();
    }

    public boolean con(String key){
        return sp.contains(key);
    }

    public void put(String key, int value)
    {
        se.putInt(key,value);
        se.commit();
    }

    public int getInt(String key){
        return sp.getInt(key,0);
    }

    public void put(String key ,String value){
        se.putString(key ,value);
        se.commit();
    }
    public String getS(String key){
        return sp.getString(key,"null");
    }

    public void put(String key ,float value){
        se.putFloat(key,value);
        se.commit();
    }

    public float getF(String key){
        return sp.getFloat(key,1.0f);

    }
}
