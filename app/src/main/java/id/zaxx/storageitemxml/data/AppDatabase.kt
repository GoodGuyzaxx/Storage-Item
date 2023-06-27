package id.zaxx.storageitemxml.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.zaxx.storageitemxml.data.dao.ItemDao
import id.zaxx.storageitemxml.data.entity.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun itemDao(): ItemDao

    companion object {
        private var instances: AppDatabase? = null

        fun getInstances(context: Context): AppDatabase{
            if (instances==null){
                instances = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instances!!
        }
    }
}