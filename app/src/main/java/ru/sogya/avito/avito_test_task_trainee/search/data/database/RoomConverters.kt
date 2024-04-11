package ru.sogya.avito.avito_test_task_trainee.search.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.PosterData
import ru.sogya.avito.avito_test_task_trainee.home.data.entity.RatingData

class RoomConverters {
    @TypeConverter
    fun objectToJson(ratingData: RatingData) = Gson().toJson(ratingData)

    @TypeConverter
    fun stringToRatingObject(string: String?): RatingData {
        return Gson().fromJson(string, RatingData::class.java)
    }

    @TypeConverter
    fun objectToJson(posterData: PosterData) = Gson().toJson(posterData)

    @TypeConverter
    fun stringToPosterObject(string: String?): PosterData {
        return Gson().fromJson(string, PosterData::class.java)
    }
}