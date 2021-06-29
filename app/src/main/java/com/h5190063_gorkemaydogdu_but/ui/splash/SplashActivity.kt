package com.h5190063_gorkemaydogdu_but.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.h5190063_gorkemaydogdu_but.util.Constants
import com.h5190063_gorkemaydogdu_but.R
import com.h5190063_gorkemaydogdu_but.ui.disneylist.DisneyListActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init(applicationContext)
    }

    private fun init(context: Context) {
        object : CountDownTimer(Constants.TOPLAM_SANIYE, Constants.SANIYE) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e(
                        context.resources.getString(R.string.counter),
                        context.resources.getString(R.string.finish)
                )
            }

            override fun onFinish() {
                ikinciSayfayiGetir(applicationContext)
            }
        }.start()
    }

    private fun ikinciSayfayiGetir(context: Context) {
        val intent = Intent(this, DisneyListActivity::class.java)
        startActivity(intent)
    }
}