package id.zaxx.storageitemxml.data.dao

import android.icu.text.StringSearch
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import id.zaxx.storageitemxml.data.entity.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item ORDER BY item_name ASC")
    fun getAll(): List<Item>

    @Query("SELECT * FROM item WHERE item_name LIKE '%' || :search || '%' ")
    fun searchByName(search: String): List<Item>

    @Query("SELECT * FROM item WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Item>

    @Insert
    fun insertAll(vararg users: Item)

    @Delete
    fun delete(user: Item)

    @Query("SELECT * FROM item WHERE uid = :uid")
    fun get(uid: Int) : Item

    @Update
    fun update(item: Item)

}