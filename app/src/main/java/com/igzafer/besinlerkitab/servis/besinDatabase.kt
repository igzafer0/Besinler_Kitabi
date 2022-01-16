package com.igzafer.besinlerkitab.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.igzafer.besinlerkitab.model.BesinModel

@Database(entities = arrayOf(BesinModel::class), version = 1)
abstract class besinDatabase : RoomDatabase() {
    abstract fun besinDao(): IBesinDAO

    //Singleton
    companion object {
        @Volatile
        private var instance: besinDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                besinDatabase::class.java,
                "besin-database"
            ).build()
    }

}