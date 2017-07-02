package com.marinesnow.minimemory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.toast


class WordFace : AppCompatActivity() {

    var cur = 0

    val printor by lazy { findViewById(R.id.printer) as TextView }
    val rightBtn by lazy { findViewById(R.id.right_btn) as Button }
    val wrongBtn by lazy { findViewById(R.id.wrong_btn)as Button }
    val puzzleBtn by lazy { findViewById(R.id.puzzle_btn)as Button }
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
}
