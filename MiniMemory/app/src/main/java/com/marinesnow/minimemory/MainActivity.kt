package com.marinesnow.minimemory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    val sh by lazy { storehelp(this) }
    var scores = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!sh.con(ShareWord.right_score)){
            sh.put(ShareWord.right_score,3)
        }
        scores = sh.getInt(ShareWord.right_score)
        score_edit.setText(scores.toString())
        enter_btn.setOnClickListener {
            val scoreText= score_edit.text.toString()
            if(scoreText.equals("")){
                toast(getString(R.string.puzzle_string))
            }else{
                var scores = score_edit.text.toString().toInt()
                if(scores <= 0)scores = 0
                if(scores > 10)scores = 10
                storehelp(this).put(ShareWord.right_score,scores);
                val intent = Intent(this,WordFace::class.java)
                startActivity(intent)
                this.finish()
            }
        }
        exit_btn.setOnClickListener { finish() }
    }
}
