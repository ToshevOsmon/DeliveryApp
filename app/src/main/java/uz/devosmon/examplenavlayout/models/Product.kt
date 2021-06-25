package uz.devosmon.examplenavlayout.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: Int,
    val perice: Int,
    val desc: String,
    val count: Int
) : Serializable