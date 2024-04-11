package ru.sogya.avito.avito_test_task_trainee.search.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.sogya.avito.avito_test_task_trainee.search.data.entity.SearchData

@Database(
    entities = [SearchData::class], version = 1
)
@TypeConverters(RoomConverters::class)
abstract class SearchDatabase : RoomDatabase() {
    abstract val searchDao: SearchDao
}