package com.marinesnow.minimemory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.word_face.*
import org.jetbrains.anko.toast


class WordFace : AppCompatActivity() {

    var cur = 0
    lateinit var resList : ArrayList<WordBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_face)
        extractRes()
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
        right_btn.setOnClickListener { resList[cur].add(3) }
        wrong_btn.setOnClickListener { resList[cur].markWrong() }
        puzzle_btn.setOnClickListener { resList[cur].markPuzzle() }
    }
}
