package com.shockwave.demogame

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var coin = 10000
    var beautyIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beauty_action.setOnClickListener(this)
        beauty_back.setOnClickListener(this)
        fullScreen()
    }

    private fun fullScreen() {
        if (Build.VERSION.SDK_INT in 12..18) {
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.systemUiVisibility = uiOptions
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.beauty_action -> if (this.coin <= 0) {
                Toast.makeText(
                    this,
                    "Your gold coins are used up. Please come back tomorrow",
                    Toast.LENGTH_SHORT
                ).show()
                return
            } else if (!this.beauty_panel.isGameRunning()) {
                this.beauty_panel.startGame()
                beauty_action.setBackgroundResource(R.drawable.ic_pause)
                this.coin += NotificationManagerCompat.IMPORTANCE_UNSPECIFIED
                this.gold_coin.text = this.coin.toString() + ""
                return
            } else {
                val nextInt = Random().nextInt(8)
                this.beautyIndex = nextInt
                this.beauty_panel.tryToStop(nextInt)
                Handler().postDelayed(
                    splashHandler(this),
                    800
                )
                return
            }
            R.id.beauty_back -> {
                finish()
                return
            }
            else -> return
        }
    }

    internal class splashHandler(private val activity: MainActivity) : Runnable {
        override fun run() {
            val unused: Int = activity.beautyIndex
            var i = 500
            var i2 = R.drawable.reward_8
            when (unused + 1) {
                0 -> i2 = R.drawable.reward_1
                1 -> i2 = R.drawable.reward_2
                2 -> {
                    i = 300
                    i2 = R.drawable.reward_3
                }
                3 -> {
                    i = 400
                    i2 = R.drawable.reward_4
                }
                4 -> {
                    i = 100
                    i2 = R.drawable.reward_5
                }
                5 -> {
                    i = 800
                    i2 = R.drawable.reward_6
                }
                6 -> {
                    i = 1000
                    i2 = R.drawable.reward_7
                }
                7 -> i2 = R.drawable.reward_8
            }
            activity.beauty_action.setBackgroundResource(R.drawable.ic_start)
            DialogSuccess(i, i2, activity).show()
        }
    }
}
