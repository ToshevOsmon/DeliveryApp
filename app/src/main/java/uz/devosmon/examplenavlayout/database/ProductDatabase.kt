package uz.devosmon.examplenavlayout.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.devosmon.examplenavlayout.models.Product
import uz.devosmon.examplenavlayout.models.ShopProduct

@Database(entities = [ShopProduct::class,Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {

        private var INSTANCE: ProductDatabase? = null

        fun getProductDatabase(context: Context): ProductDatabase {

            if (INSTANCE == null) {

                INSTANCE =
                    Room.databaseBuilder(context, ProductDatabase::class.java, "productDb")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }

}