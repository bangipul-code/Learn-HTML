package com.example.myapp_learnhtml.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapp_learnhtml.data.model.MateriPage
import com.example.myapp_learnhtml.data.model.MateriTopic

@Database(
    entities = [MateriTopic::class, MateriPage::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MateriDatabase : RoomDatabase() {

    abstract fun materiDao(): MateriDao

    companion object {
        @Volatile
        private var INSTANCE: MateriDatabase? = null

        fun getInstance(context: Context): MateriDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MateriDatabase::class.java,
                    "materi_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
