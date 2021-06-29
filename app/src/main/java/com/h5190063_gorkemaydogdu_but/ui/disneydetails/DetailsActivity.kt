package com.h5190063_gorkemaydogdu_but.ui.disneydetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.h5190063_gorkemaydogdu_but.R
import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponseItem
import com.h5190063_gorkemaydogdu_but.databinding.ActivityDetailsBinding
import com.h5190063_gorkemaydogdu_but.util.Constants
import com.h5190063_gorkemaydogdu_but.util.GlideUtil
import com.h5190063_gorkemaydogdu_but.util.ObjectUtil

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()
    }

    fun init() {
        val tiklananFilmString = intent.getStringExtra(Constants.TIKLANAN_FILM)
        val filmler: DisneyResponseItem? = ObjectUtil.jsonStringToFilm(tiklananFilmString)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            GlideUtil().apply {
                imageViewFilmKapak.resimCek(filmler!!.poster)
            }
            textViewDetailID.text = filmler!!.id.toString()
            textViewDetailName.text = filmler!!.name
            textViewAciklama.text = filmler!!.description
            textViewKonusu.text = filmler!!.plot
            textViewDiziSaat.text = filmler!!.playtime
            textViewYayinTarihi.text = filmler!!.release
        }
    }
}