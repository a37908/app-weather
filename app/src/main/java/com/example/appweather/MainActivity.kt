package com.example.appweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appweather.api.ApiViewModel
import com.example.appweather.databinding.ActivityMainBinding
import com.example.appweather.models.WeatherShowModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToLong


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiViewModel: ApiViewModel by viewModels()
    private var currentIndexCity = 0

    private lateinit var listCity: MutableList<String>
    lateinit var adapterSearch: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataApi()
    }

    private fun getDataApi(){
        apiViewModel.weatherModelViewModel().observe(this) {
            binding.layoutMain.visibility = View.VISIBLE
            binding.progressbar.visibility = View.GONE

            val listWeather = it as MutableList
            val value = getValueByWeather(listWeather[currentIndexCity].data.currently.icon)
            listCity = mutableListOf()
            listWeather.forEach { itWeatherModel ->
                listCity.add(itWeatherModel.name)
            }

            setValueView(listWeather[currentIndexCity], value!!)
            search()
            setDate()
            setListWeek(listWeather[currentIndexCity].data.daily)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setListWeek(listDaily: List<Daily>) {
        binding.tvForecast.text =
            "Dự báo ${if (listDaily.size >= 7) 7 else listDaily.size} ngày tiếp theo"
        val adapterWeek = AdapterWeek(listDaily)
        binding.rcvWeek.adapter = adapterWeek
        binding.rcvWeek.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    @SuppressLint("SetTextI18n")
    private fun setValueView(weatherModel: WeatherModel, weatherShowModel: WeatherShowModel) {
        binding.tvNameCity.text = weatherModel.name
        binding.tvTemperature.text = "${weatherModel.data.currently.temperature.roundToLong()}°C"
        binding.tvHumidity.text = "Độ ẩm: ${(weatherModel.data.currently.humidity * 100).toInt()}%"
        binding.tvWindSpeed.text = "Tốc độ gió: ${weatherModel.data.currently.windSpeed}km/h"
        binding.tvSuggest.text = weatherShowModel.suggest
        binding.imgWeather.setImageResource(weatherShowModel.icon)
        binding.tvNameWeather.text = weatherShowModel.nameWeather
    }

    private fun setDate() {
        val currentDateTime = LocalDateTime.now()
        val vietnameseLocale = Locale("vi", "VN")
        val formatter = DateTimeFormatter.ofPattern("EEE, d MMMM HH:mm", vietnameseLocale)
        val formattedDateTime = currentDateTime.format(formatter)
        binding.tvDateTime.text = formattedDateTime.toString()
    }

    private fun search() {
        adapterSearch = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            listCity
        )
        binding.lvCity.adapter = adapterSearch

        binding.lvCity.setOnItemClickListener { parent, view, position, id ->
            currentIndexCity = position
            getDataApi()
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (listCity.contains(query)) {
                    adapterSearch.filter.filter(query)
                } else {
                    Toast.makeText(this@MainActivity, "Không tìm thấy thành phố", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    binding.lvCity.visibility = View.VISIBLE
                    binding.viewLV.visibility = View.VISIBLE
                } else {
                    binding.lvCity.visibility = View.GONE
                    binding.viewLV.visibility = View.GONE
                }
                adapterSearch.filter.filter(newText)
                return false
            }
        })
    }

    override fun onRestart() {
        super.onRestart()

        recreate()
    }
}