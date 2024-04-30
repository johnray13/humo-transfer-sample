package ru.appsmile.humo_transfer_sample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.appsmile.humo_transfer_sample.countriesInfo.Countries
import ru.appsmile.humo_transfer_sample.databinding.ActivitySecondBinding
import ru.appsmile.humo_transfer_sample.extra.ExitApp

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var rcView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private var popCountriesList = listOf<Countries>(
        Countries("Таджикистан", R.drawable.tjk,"1.0","Таджикистан","TJS"),
        Countries("Россия", R.drawable.rus,"0.1172","Россию","RUB"),
        Countries("Узбекистан", R.drawable.uzb,"0.0009","Узбекистан","UZS")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences("my_storage",Context.MODE_PRIVATE)


    }

    override fun onResume() {
        super.onResume()
        initRcView()

        binding.sentm.setOnClickListener {
            startActivity(Intent(this,ThirdActivity::class.java))
        }

        binding.cardV3.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=tj.humo.transfer"))
            )
        }

        binding.exitBlock.setOnClickListener{
            ExitApp.showDialog(this,object:ExitApp.Listener{
                override fun onClick() {

                    sharedPreferences.edit().clear().apply()

                    startActivity(Intent(this@SecondActivity,MainActivity::class.java))
                    finish()
                }
            })
        }
    }

    private fun initRcView() {
        rcView = binding.rcView
        rcView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = CountryAdapter(popCountriesList,true)
        rcView.adapter = adapter
    }
}