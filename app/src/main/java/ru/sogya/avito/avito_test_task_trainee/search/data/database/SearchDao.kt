package ru.sogya.avito.avito_test_task_trainee.search.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.sogya.avito.avito_test_task_trainee.search.data.entity.SearchData
import ru.sogya.avito.avito_test_task_trainee.search.data.util.Constants

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(searchData: SearchData)

    @Query("SELECT * FROM ${Constants.SEARCH_TABLE}")
    fun getAllSearches(): Flow<List<SearchData>>

    @Query(
        "DELETE FROM ${Constants.SEARCH_TABLE} where id NOT IN" +
                " (SELECT id from ${Constants.SEARCH_TABLE} ORDER BY id DESC LIMIT 20)"
    )
    suspend fun deleteOldSearches()
}