package uz.devosmon.examplenavlayout.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product")
data class ShopProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val image: Int,
    val perice: Int,
    val desc: String,
    val count: Int
) : Serializable