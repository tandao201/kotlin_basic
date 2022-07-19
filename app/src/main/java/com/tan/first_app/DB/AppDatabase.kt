package com.tan.first_app.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tan.first_app.Models.Member
import com.tan.first_app.Interfaces.dao.IMemberDao

@Database(entities = [Member::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): IMemberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context?): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context?): AppDatabase {
            return Room.databaseBuilder(
                context!!.applicationContext,
                AppDatabase::class.java,

                "member_database"
            ).build()
        }
    }
}