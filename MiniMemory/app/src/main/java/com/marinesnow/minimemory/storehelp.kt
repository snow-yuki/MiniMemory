package com.marinesnow.minimemory

/**
 * Created by mei on 2017/7/2.
 */

import android.content.SharedPreferences
import android.content.Context

class storehelp {

    private var sp: SharedPreferences? = null
    private var se: SharedPreferences.Editor? = null

    constructor(cp: Context, name: String) {
        sp = cp.getSharedPreferences(name, 0)
        se = sp!!.edit()
    }

    constructor(c: Context) {
        sp = c.getSharedPreferences(MAIN_SET, 0)
        se = sp!!.edit()
    }

    fun con(key: String): Boolean {
        return sp!!.contains(key)
    }

    fun put(key: String, value: Int) {
        se!!.putInt(key, value)
        se!!.commit()
    }

    fun getInt(key: String): Int {
        return sp!!.getInt(key, 0)
    }

    fun put(key: String, value: String) {
        se!!.putString(key, value)
        se!!.commit()
    }

    fun getS(key: String): String {
        return sp!!.getString(key, "null")
    }

    fun put(key: String, value: Float) {
        se!!.putFloat(key, value)
        se!!.commit()
    }

    fun getF(key: String): Float {
        return sp!!.getFloat(key, 1.0f)

    }

    companion object {
        val MAIN_SET = "main"
    }
}
