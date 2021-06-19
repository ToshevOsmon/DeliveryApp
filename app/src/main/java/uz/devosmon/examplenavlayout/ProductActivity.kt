package uz.devosmon.examplenavlayout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_product.*
import uz.devosmon.examplenavlayout.models.Product
import uz.devosmon.examplenavlayout.models.ShopProduct
import uz.devosmon.examplenavlayout.viewmodel.ShopProductViewModel

class ProductActivity : AppCompatActivity() {
    lateinit var product: Product
    lateinit var newProduct: ShopProduct
    var isFav: Boolean = false
    var count: Int = 0
    var sum = 0

    lateinit var shopProductViewModel: ShopProductViewModel

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        product = intent.getSerializableExtra("product") as Product

        imgToolBarBtnBack.setOnClickListener {
            finish()
        }
        imgToolBarBtnFav.setOnClickListener {
            if (isFav) {
                imgToolBarBtnFav.setImageResource(R.drawable.ic_favorite_border)
                isFav = false
            } else {
                imgToolBarBtnFav.setImageResource(R.drawable.ic_favorite)
                isFav = true
            }
        }

        imgItem.setImageResource(product.image)
        productName.text = product.name
        productDesc.text = product.desc
        productPrice.text = "Perice: " + product.perice.toString() + ".00 $"

        minus.setOnClickListener {

            count = product.count

            if (count > 1) {
                count--
                countProduct.text = count.toString()
                sum = count * product.perice
                productPrice.text = "Perice: " + sum.toString() + ".00 $"
            } else {
                count = 1
                countProduct.text = count.toString()
                sum = count * product.perice
                productPrice.text = "Perice: " + sum.toString() + ".00 $"
            }
        }
        plus.setOnClickListener {

            count++
            countProduct.text = count.toString()
            sum = count * product.perice
            productPrice.text = "Perice: " + sum.toString() + ".00 $"

        }


        shopProductViewModel = ViewModelProviders.of(this).get(ShopProductViewModel::class.java)

        btnAddToCart.setOnClickListener {

            Log.d("TTTT", sum.toString() + " \n Count: " + count.toString())

            newProduct =
                ShopProduct(product.id, product.name, product.image, sum, product.desc, count)

            btnAddToCart.text = "Add To Cart"
            btnAddToCart.setBackgroundResource(R.drawable.bg_add_cart)
            Log.d("TTTT", "viewModelga yitib kelidi")

            shopProductViewModel.insertShopProduct(newProduct)
            Log.d("TTTT", "viewModel ishladi")

            btnAddToCart.text = "Added Cart"
            btnAddToCart.isClickable = false


            Log.d("TTTT", newProduct.toString())

        }


    }


}
