package ru.appsmile.humo_transfer_sample

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import ru.appsmile.humo_transfer_sample.countriesInfo.Countries


class CountryAdapter(
    private var listCountries: List<Countries>,
    private val isPopularXml: Boolean = false
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PopularCountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.pop_country_image)
        val tvText: TextView = view.findViewById(R.id.country_txt)
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.country_image)
        val tvText: TextView = view.findViewById(R.id.country_txt_2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (isPopularXml) {
            true -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pop_countries_item, parent, false)
                PopularCountryViewHolder(itemView)
            }

            false -> {
                val itemView2 = LayoutInflater.from(parent.context)
                    .inflate(R.layout.countries_item, parent, false)
                MyViewHolder(itemView2)
            }
        }
    }

    override fun getItemCount(): Int = listCountries.size


    fun setFilterList(list: List<Countries>) {
        this.listCountries = list
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PopularCountryViewHolder -> {
                holder.imageView.setImageResource(listCountries[position].image)
                holder.tvText.text = listCountries[position].countryName
                holder.itemView.setOnClickListener {
                    val intent=Intent(holder.itemView.context,FourthActivity::class.java)
                    intent.putExtra("name_country",listCountries[position].countrySend)
                    intent.putExtra("initial_country",listCountries[position].code)
                    intent.putExtra("course_country",listCountries[position].rate)
                    holder.itemView.context.startActivity(intent)
                }
            }

            is MyViewHolder -> {
                holder.imageView.setImageResource(listCountries[position].image)
                holder.tvText.text = listCountries[position].countryName
                holder.itemView.setOnClickListener {
                    val intent=Intent(holder.itemView.context,FourthActivity::class.java)
                    intent.putExtra("name_country",listCountries[position].countryName)
                    intent.putExtra("initial_country",listCountries[position].code)
                    intent.putExtra("course_country",listCountries[position].rate)
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

}