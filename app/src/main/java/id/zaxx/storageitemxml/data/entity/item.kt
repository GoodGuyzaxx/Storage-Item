package id.zaxx.storageitemxml.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class Item (
    @PrimaryKey val uid: Int?,
    @ColumnInfo ( name = "item_name") val itemName: String?,
    @ColumnInfo ( name = "info_item") val infoItem: String?,
    )