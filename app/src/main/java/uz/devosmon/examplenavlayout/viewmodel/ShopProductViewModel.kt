package uz.devosmon.examplenavlayout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.devosmon.examplenavlayout.models.ShopProduct
import uz.devosmon.examplenavlayout.repostory.ProductRepository

class ShopProductViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var allShopProducts: LiveData<List<ShopProduct>>
    lateinit var repository: ProductRepository

    init {
        repository = ProductRepository(app)
        allShopProducts = repository.getAllShopProducts()
    }

    fun getAllShopProductsObservce(): LiveData<List<ShopProduct>> {
        return allShopProducts
    }

    fun insertShopProduct(shopProduct: ShopProduct) {
        repository.insertShopProduct(shopProduct)
    }

    fun updateShopProduct(shopProduct: ShopProduct) {
        repository.updateShopProduct(shopProduct)
    }

    fun deleteShopProduct(shopProduct: ShopProduct) {
        repository.deleteShopProduct(shopProduct)
    }

}