package ru.appsmile.humo_transfer_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.appsmile.humo_transfer_sample.countriesInfo.Countries
import ru.appsmile.humo_transfer_sample.databinding.ActivityThirdBinding
import java.util.Locale


class ThirdActivity : AppCompatActivity() {

    private lateinit var binding:ActivityThirdBinding

    private lateinit var rcView: RecyclerView
    private lateinit var adapter: CountryAdapter

    private var countries = listOf<Countries>(
        Countries("Таджикистан", R.drawable.tjk,"1.0","Таджикистан","TJS"),
        Countries("Россия", R.drawable.rus,"0.1174","Россию","RUB"),
        Countries("Узбекистан", R.drawable.uzb,"0.0866","Узбекистан","UZB"),
        Countries("Казахстан",R.drawable.kaz,"0.2475","Казахстан","KZT"),
        Countries("ОАЭ",R.drawable.oae,"2.9","ОАЭ","AED"),
        Countries("Корея",R.drawable.kor,"0.0079","Корею","KRW"),
        Countries("Украина",R.drawable.uk,"0.2758","Украину","UAH"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()

        initRcView()

        binding.back.setOnClickListener {
           onBackPressed()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {

        if (query!=null){
            val filteredList =ArrayList<Countries>()
            for (i in countries){
                if (i.countryName.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()){
                Toast.makeText(this,"Cтрана не найдена, Проверьте, правильно ли вы ввели страну", Toast.LENGTH_SHORT).show()
                adapter.setFilterList(filteredList)
            }else{
                adapter.setFilterList(filteredList)
            }
        }
    }


    private fun initRcView(){
        rcView=binding.rcView
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter(countries)
        rcView.adapter=adapter

    }

}