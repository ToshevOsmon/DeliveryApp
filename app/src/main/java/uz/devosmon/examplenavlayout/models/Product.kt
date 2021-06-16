package uz.devosmon.examplenavlayout.models

import java.io.Serializable

data class Product(
    val id: Int,
    val name: String,
    val image: Int,
    val perice: Int,
    val desc: String
) : Serializable