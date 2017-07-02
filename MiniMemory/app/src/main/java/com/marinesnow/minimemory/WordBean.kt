package com.marinesnow.minimemory

/**
 * Created by mei on 2017/7/2.
 */
data class WordBean(var word : String,var count : Int){
    constructor():this("?",0)
    fun markWrong(){
        count--
    }
    fun markPuzzle():Boolean{
        return (++count)<10
    }
    fun add(c : Int):Boolean{
        count+=c
        return count<10
    }
}