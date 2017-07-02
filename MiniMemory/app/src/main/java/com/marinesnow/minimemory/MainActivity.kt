package com.marinesnow.minimemory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.enter_btn).setOnClickListener {
            val intent = Intent(this,WordFace::class.java)
            startActivity(intent)
        }
        findViewById(R.id.exit_btn).setOnClickListener { finish() }
    }
}
