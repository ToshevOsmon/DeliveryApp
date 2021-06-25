package uz.devosmon.examplenavlayout.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "shop_product")
data class ShopProduct(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: Int,
    val perice: Int,
    val desc: String,
    val count: Int
) : Serializable