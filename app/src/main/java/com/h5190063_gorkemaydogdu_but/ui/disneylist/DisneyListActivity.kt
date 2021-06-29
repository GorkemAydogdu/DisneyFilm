package com.h5190063_gorkemaydogdu_but.ui.disneylist

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.h5190063_gorkemaydogdu_but.R
import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponseItem
import com.h5190063_gorkemaydogdu_but.databinding.ActivityDisneylistBinding
import com.h5190063_gorkemaydogdu_but.ui.disneydetails.DetailsActivity
import com.h5190063_gorkemaydogdu_but.util.Constants
import com.h5190063_gorkemaydogdu_but.util.ItemClickListener
import com.h5190063_gorkemaydogdu_but.util.ObjectUtil

class DisneyListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisneylistBinding
    var disneyListActivityViewModel: DisneyViewModel? = null
    private lateinit var disneyAdapter: DisneyAdapter
    var filmler: List<DisneyResponseItem>? = null
    lateinit var progress: ProgressDialog
    lateinit var detayEkrani: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disneylist)
        init()
    }

    private fun init() {
        binding = ActivityDisneylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        progressDialog()
        binding.apply {
            searchviewFilm.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchFilm(newText!!)
                    return false
                }
            })
            sirala()
            switchListe()
        }
    }

    private fun progressDialog() {
        progress = ProgressDialog(this@DisneyListActivity)
        progress.setTitle(resources.getString(R.string.proggresdialogBaslik))
        progress.setMessage(resources.getString(R.string.proggresdialogMesaj))
        progress.show()
    }

    private fun switchListe() {
        binding.apply {
            switchListeTuru.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    rcvFilm.layoutManager = StaggeredGridLayoutManager(Constants.LINEAR_LAYOUT, StaggeredGridLayoutManager.VERTICAL)
                } else {
                    rcvFilm.layoutManager = StaggeredGridLayoutManager(Constants.GRID_LAYOUT, StaggeredGridLayoutManager.VERTICAL)
                }
            }
        }
    }

    private fun sirala() {
        binding.apply {
            buttonSirala.setOnClickListener {
                val builder = AlertDialog.Builder(this@DisneyListActivity)
                builder.setTitle(resources.getString(R.string.seciniz))
                val sirala = arrayOf(resources.getString(R.string.ismeGoreArtan), resources.getString(R.string.ismeGoreAzalan))
                builder.setItems(sirala) { dialog, pozisyon ->
                    when (pozisyon) {
                        0 -> {
                            filmler = filmler!!.sortedBy { it.name }
                            initRecycleView(filmler!!)
                        }
                        1 -> {
                            filmler = filmler!!.sortedByDescending { it.name }
                            initRecycleView(filmler!!)
                        }
                    }
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.uyarı))
        builder.setMessage(resources.getString(R.string.alertCikis))
        builder.setPositiveButton(resources.getString(R.string.Evet)) { dialogInterface: DialogInterface, i: Int ->
            System.exit(0)
        }
        builder.setNegativeButton(resources.getString(R.string.Hayir)) { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.cancel()
        }
        builder.show()
    }

    private fun searchFilm(string: String) {
        string?.let {
            var searchList = filmler!!.filter { it.name.toLowerCase().contains(string.toLowerCase()) }
            initRecycleView(searchList)
        }
    }

    private fun initRecycleView(filmler: List<DisneyResponseItem>) {
        binding.apply {
            disneyAdapter = DisneyAdapter(filmler!!, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    val tiklananFilm: DisneyResponseItem = filmler[position]
                    Toast.makeText(applicationContext, tiklananFilm.name, Toast.LENGTH_SHORT).show()
                    initDetayEkrani(tiklananFilm)
                }
            })
            rcvFilm.adapter = disneyAdapter
            rcvFilm.layoutManager = StaggeredGridLayoutManager(Constants.GRID_LAYOUT, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun initDetayEkrani(tiklananFilm: DisneyResponseItem) {
        detayEkrani = Intent(this, DetailsActivity::class.java)
        val tiklananFilmString = ObjectUtil.filmToJsonString(tiklananFilm)
        detayEkrani.putExtra(Constants.TIKLANAN_FILM, tiklananFilmString)
        startActivity(detayEkrani)
    }

    private fun initViewModel() {
        disneyListActivityViewModel = DisneyViewModel()
        disneyListActivityViewModel?.apply {
            filmlerLiveData?.observe(this@DisneyListActivity, Observer {
                it.run {
                    filmler = it
                    initRecycleView(it)
                    progress.dismiss()
                }
            })
            error?.observe(this@DisneyListActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
            loading?.observe(this@DisneyListActivity, Observer {
            })
        }
    }
}