package com.capriproxy.runapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.capriproxy.runapp.databinding.ActivityTimerBinding
import java.util.*

class TimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimerBinding
    var time = 0
    var started = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun View.goHome() {
        val intent = Intent(this@TimerActivity, MainActivity::class.java)
        startActivity(intent)
    }
    fun View.startTimer() {
        runTimer()
    }
    fun View.reset() {
        time = 0
        started = false
        updateText()
    }
    private fun pause() {
        started = false
        binding.startLabel.text = "Start"
    }
    fun updateText() {
        val minutes: Int = time / 360000
        val secs: Int = time % 6000 / 100
        val milli: Int = time % 100
        var timeString: String = java.lang.String
            .format(
                Locale.getDefault(),
                "%02d:%02d:%02d",
                minutes, secs, milli
            )
        binding.timerText.text = timeString
    }
    private fun runTimer() {
        if(started)   {
            return pause()
        }
        binding.startLabel.text = "Pause"
        started = true
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                updateText()
                if (started) {
                    time+=1
                    handler.postDelayed(this, 10)
                }
            }
        })
    }
}