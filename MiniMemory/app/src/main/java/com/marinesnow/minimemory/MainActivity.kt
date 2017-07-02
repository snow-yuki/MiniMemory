package com.marinesnow.minimemory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enter_btn.setOnClickListener {
            val scoreText = score_edit.text.toString()
            if(scoreText == null||scoreText.equals("")){
                toast(getString(R.string.puzzle_string))
            }else{
                var scores = score_edit.text.toString().toInt()
                if(scores <= 0)scores = 0
                if(scores > 10)scores = 10
                storehelp(this).put(ShareWord.right_score,scores);
                val intent = Intent(this,WordFace::class.java)
                startActivity(intent)
            }
        }
        exit_btn.setOnClickListener { finish() }

    }
}
