package com.example.appweather

import com.example.appweather.models.WeatherShowModel
import java.util.*

fun getValueByWeather(nameImage: String): WeatherShowModel? {
    return when (nameImage) {
        "clear-day" -> {
            WeatherShowModel(
                R.drawable.img_clear_day,
                "Ngày nắng",
                "Trời có thể sẽ nắng nên mang theo mũ và áo nắng."
            )
        }
        "clear-night" -> {
            WeatherShowModel(
                R.drawable.img_clear_night,
                "Đêm tối",
                "Trời tối, bạn cần đảm bảo an toàn cho bản thân, cẩn thận với tầm nhìn hạn chế, thận trọng với các phương tiện giao thông."
            )
        }
        "cloudy" -> {
            WeatherShowModel(
                R.drawable.img_cloudy,
                "Nhiều mây",
                "Trời nhiều mây, khi ra đường bạn cần đảm bảo sự thoải mái và an toàn cho bản thân."
            )
        }
        "fog" -> {
            WeatherShowModel(
                R.drawable.img_fog,
                "Sương mù",
                "Trời có thể sẽ có sương mù, sẽ làm giảm tầm nhìn nên giảm tốc độ khi đi trên đường."
            )
        }
        "partly-cloudy-day" -> {
            WeatherShowModel(
                R.drawable.img_partly_cloudy_day,
                "Ngày nhiều mây",
                "Ban ngày trời nhiều mây, bạn nên đảm bảo cả sự thoải mái và an toàn cho bản thân, chuẩn bị quần áo phù hợp."
            )
        }
        "partly-cloudy-night" -> {
            WeatherShowModel(
                R.drawable.img_partly_cloudy_night,
                "Đêm nhiều mây",
                "Ban đêm nhiều mây, khi ra đường cần chú ý đến việc đảm bảo an toàn giao thông vì trời sẽ tối hơn."
            )
        }
        "rain", "sleet" -> {
            WeatherShowModel(
                R.drawable.img_rain,
                "Mưa",
                "Trời có thể sẽ mưa, bạn nên chuẩn bị quần áo phù hợp: nên mang theo ô, áo mưa,  phụ kiện bảo vệ, ..., cẩn thận khi tham gia giao thông."
            )
        }
        else -> {
            return null
        }
    }
}


fun getImageByWeather(nameImage: String): Int? {
    return when (nameImage) {
        "clear-day" -> {
            R.drawable.img_clear_day
        }
        "clear-night" -> {
            R.drawable.img_clear_night
        }
        "cloudy" -> {
            R.drawable.img_cloudy
        }
        "fog" -> {
            R.drawable.img_fog
        }
        "partly-cloudy-day" -> {
            R.drawable.img_partly_cloudy_day
        }
        "partly-cloudy-night" -> {
            R.drawable.img_partly_cloudy_night
        }
        "rain", "sleet" -> {
            R.drawable.img_rain
        }
        else -> {
            return null
        }
    }
}

fun getNameOfDayNextTime(day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, day)

    return if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
        "Chủ nhật"
    } else {
        "Thứ ${calendar.get(Calendar.DAY_OF_WEEK)}"
    }
}



