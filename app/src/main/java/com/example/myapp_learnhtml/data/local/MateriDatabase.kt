package com.example.myapp_learnhtml.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapp_learnhtml.data.model.MateriPage
import com.example.myapp_learnhtml.data.model.MateriProgress
import com.example.myapp_learnhtml.data.model.MateriTopic
import com.example.myapp_learnhtml.data.model.LatihanProgress
import com.example.myapp_learnhtml.data.model.LatihanQuestion
import com.example.myapp_learnhtml.data.model.PraktikProgress

@Database(
    entities = [
        MateriTopic::class,
        MateriPage::class,
        MateriProgress::class,
        LatihanQuestion::class,
        LatihanProgress::class,
        PraktikProgress::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MateriDatabase : RoomDatabase() {

    abstract fun materiDao(): MateriDao
    abstract fun latihanDao(): LatihanDao
    abstract fun praktikDao(): PraktikDao

    companion object {
        @Volatile
        private var INSTANCE: MateriDatabase? = null

        fun getInstance(context: Context): MateriDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MateriDatabase::class.java,
                    "materi_database"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }
}
