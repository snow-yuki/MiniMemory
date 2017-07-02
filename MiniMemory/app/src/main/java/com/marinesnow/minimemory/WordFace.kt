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
                0 -> printer.setText(BuleString.toTextView(current))
            }
            super.handleMessage(msg)
        }
    } }

    var cur = 0
    var current = ""
    var rightScore = 3;
    val sh by lazy { storehelp(this) }
    lateinit var resList : ArrayList<WordBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_face)
        initUI()
        extractRes()
        afterClick()
    }

    fun extractRes(){
        rightScore = sh.getInt(ShareWord.right_score);

        val instreams = assets.open("res.txt")
        val size = instreams.available()
        val buffer : ByteArray = kotlin.ByteArray(size)
        instreams.read(buffer)
        val originString = String(buffer)
        resList = BuleString.generateList(originString)
        for(it in resList.indices){
            println("---it----${resList[it]}")
        }
    }

    fun initUI(){
        relative_face.setOnClickListener {showAnswer()}
        printer.setOnClickListener {showAnswer()}

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
    }

    fun showAnswer(){
        if(BuleString.notHasStar(current)){
            return
        }
        current = BuleString.removeStar(current)
        mHandler.sendEmptyMessage(0)
    }

    fun afterClick(){
        cur = cur%resList.size
        current = resList[cur].word
        mHandler.sendEmptyMessage(0)
    }
}
