package uz.devosmon.examplenavlayout.database

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.devosmon.examplenavlayout.models.ShopProduct


@Dao
interface ProductDao {

    @Query("SELECT * FROM  product")
    fun getAllProduct(): LiveData<List<ShopProduct>>

    @Insert
    fun insertProduct(product: Array<out ShopProduct?>)

    @Update
    fun updateProduct(product: Array<out ShopProduct?>)

    @Delete
    fun deleteProduct(product: Array<out ShopProduct?>)


}