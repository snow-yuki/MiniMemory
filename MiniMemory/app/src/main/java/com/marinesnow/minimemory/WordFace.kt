package com.marinesnow.minimemory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.word_face.*
import org.jetbrains.anko.toast


class WordFace : AppCompatActivity() {

    private val mHandler by lazy { object : Handler(){
        override fun handleMessage(msg: Message?) {
            when(msg?.what){
                0 -> printer.setText(current)
            }
            super.handleMessage(msg)
        }
    } }

    var cur = 0
    var current = ""
    lateinit var resList : ArrayList<WordBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_face)
        extractRes()
        afterClick()
    }

    fun extractRes(){
        val instreams = assets.open("res.txt")
        val size = instreams.available()
        val buffer : ByteArray = kotlin.ByteArray(size)
        instreams.read(buffer)
        val originString = String(buffer)
        toast(originString)
    }

    fun initUI(){
        right_btn.setOnClickListener {
            if(!resList[cur].add(3)){
                resList.removeAt(cur)
            }else cur++
            afterClick()
        }
        wrong_btn.setOnClickListener {
            resList[cur].markWrong()
            cur++
            afterClick()
        }
        puzzle_btn.setOnClickListener {
            if(!resList[cur].markPuzzle()){
                resList.removeAt(cur)
            }else cur++
            afterClick()
        }
        printer.setOnClickListener {
            
        }
    }

    fun afterClick(){
        cur = cur%resList.size
        current = resList[cur].word
        mHandler.sendEmptyMessage(0)
    }
}
