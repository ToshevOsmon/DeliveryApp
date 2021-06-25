package uz.devosmon.examplenavlayout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uz.devosmon.examplenavlayout.models.Product
import uz.devosmon.examplenavlayout.models.ShopProduct
import uz.devosmon.examplenavlayout.repostory.ProductRepository

class ShopProductViewModel(app: Application) : AndroidViewModel(app) {

    var allShopProducts: LiveData<List<ShopProduct>>
    var repository: ProductRepository = ProductRepository(app)
    var allProducts: LiveData<List<Product>>

    init {
        allShopProducts = repository.getAllShopProducts()
        allProducts = repository.getAllProducts()
    }

    //product list
    fun getALlProductObservce(): LiveData<List<Product>> {
        return allProducts
    }

    //insert product
    fun insertAllProduct(product: Product) {
        repository.insertProduct(product)
    }

    //shopping list product
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