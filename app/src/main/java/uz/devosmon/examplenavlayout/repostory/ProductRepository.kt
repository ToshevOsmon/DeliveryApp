package uz.devosmon.examplenavlayout.repostory

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import uz.devosmon.examplenavlayout.database.ProductDao
import uz.devosmon.examplenavlayout.database.ProductDatabase
import uz.devosmon.examplenavlayout.models.ShopProduct

class ProductRepository(app: Application) {

    private lateinit var productDao: ProductDao

    private lateinit var products: LiveData<List<ShopProduct>>

    init {

        val database = ProductDatabase.getProductDatabase(app)
        productDao = database.productDao()
        products = productDao.getAllProduct()
    }

    fun getAllShopProducts(): LiveData<List<ShopProduct>> {
        return products
    }

    fun insertShopProduct(shopProduct: ShopProduct) {
        InsertShopProductAsyncTask(productDao).execute(shopProduct)
    }

    fun updateShopProduct(shopProduct: ShopProduct) {
        UpdateShopProductAsyncTask(productDao).execute(shopProduct)
    }

    fun deleteShopProduct(shopProduct: ShopProduct) {
        DeleteShopProductAsyncTask(productDao).execute(shopProduct)
    }

}

class InsertShopProductAsyncTask(productDao: ProductDao) : AsyncTask<ShopProduct, Void, Void>() {

    private lateinit var productDao: ProductDao

    init {
        this.productDao = productDao
    }

    override fun doInBackground(vararg p0: ShopProduct?): Void? {
        productDao.insertProduct(p0)
        return null
    }

}

class UpdateShopProductAsyncTask(productDao: ProductDao) : AsyncTask<ShopProduct, Void, Void>() {

    private lateinit var productDao: ProductDao

    init {
        this.productDao = productDao
    }

    override fun doInBackground(vararg p0: ShopProduct?): Void? {
        productDao.updateProduct(p0)
        return null
    }

}

class DeleteShopProductAsyncTask(productDao: ProductDao) : AsyncTask<ShopProduct, Void, Void>() {

    private lateinit var productDao: ProductDao

    init {
        this.productDao = productDao
    }

    override fun doInBackground(vararg p0: ShopProduct?): Void? {
        productDao.deleteProduct(p0)
        return null
    }

}