package com.example.appweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToLong

class AdapterWeek(private val listDayOfWeek: List<Daily>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ItemWeekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNameDayOfWeek: TextView
        var imgWeather: ImageView
        var tvTemperature: TextView

        init {
            tvNameDayOfWeek = itemView.findViewById(R.id.tvNameDayOfWeek)
            imgWeather = itemView.findViewById(R.id.imgWeather)
            tvTemperature = itemView.findViewById(R.id.tvTemperature)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWeekViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_week, parent, false)
        return ItemWeekViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemWeekViewHolder)
        val item = listDayOfWeek[position]

        holder.tvNameDayOfWeek.text = getNameOfDayNextTime(position + 1)
        holder.imgWeather.setImageResource(getImageByWeather(item.icon)!!)
        holder.tvTemperature.text =
            "${item.temperatureMax.roundToLong()}°C/${item.temperatureMin.roundToLong()}°C"
    }

    override fun getItemCount(): Int = if(listDayOfWeek.size >= 7) 7 else listDayOfWeek.size
}