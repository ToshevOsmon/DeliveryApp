package uz.devosmon.examplenavlayout.repostory

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import uz.devosmon.examplenavlayout.database.ProductDao
import uz.devosmon.examplenavlayout.database.ProductDatabase
import uz.devosmon.examplenavlayout.models.Product
import uz.devosmon.examplenavlayout.models.ShopProduct

class ProductRepository(app: Application) {

    private var productDao: ProductDao

    private var products: LiveData<List<ShopProduct>>

    private var lists: LiveData<List<Product>>

    init {

        val database = ProductDatabase.getProductDatabase(app)
        productDao = database.productDao()
        products = productDao.getAllProduct()
        lists = productDao.getProducts()
    }

    //product list
    fun getAllProducts(): LiveData<List<Product>> {
        return lists
    }


    fun insertProduct(product: Product) {
        InsertProductAsyncTask(productDao).execute(product)
    }

    //shopping list

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

class InsertShopProductAsyncTask(private var productDao: ProductDao) : AsyncTask<ShopProduct, Void, Void>() {

    override fun doInBackground(vararg p0: ShopProduct?): Void? {
        productDao.insertProduct(p0)
        return null
    }

}

class InsertProductAsyncTask(private var productDao: ProductDao) : AsyncTask<Product, Void, Void>() {

    override fun doInBackground(vararg p0: Product?): Void? {
        productDao.insert(p0)
        return null
    }

}


class UpdateShopProductAsyncTask(private var productDao: ProductDao) : AsyncTask<ShopProduct, Void, Void>() {

    override fun doInBackground(vararg p0: ShopProduct?): Void? {
        productDao.updateProduct(p0)
        return null
    }

}

class DeleteShopProductAsyncTask(private var productDao: ProductDao) : AsyncTask<ShopProduct, Void, Void>() {

    override fun doInBackground(vararg p0: ShopProduct?): Void? {
        productDao.deleteProduct(p0)
        return null
    }

}