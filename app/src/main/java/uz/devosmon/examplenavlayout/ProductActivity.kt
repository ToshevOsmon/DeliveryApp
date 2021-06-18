package uz.devosmon.examplenavlayout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_product.*
import uz.devosmon.examplenavlayout.models.Product

class ProductActivity : AppCompatActivity() {
    lateinit var product: Product
    lateinit var newProduct: Product
    var isFav: Boolean = false
     var newList = ArrayList<Product>()
    var count: Int = 1
    var isAddCart = false
    var sum = 0

    @SuppressLint("ResourceAsColor")
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

        newList = ArrayList()
        newProduct = Product(product.id, product.name, product.image, sum, product.desc, count)

        btnAddToCart.setOnClickListener {

            if (isAddCart) {
                btnAddToCart.text = "Add To Cart"
                btnAddToCart.setBackgroundResource(R.drawable.bg_add_cart)
                isAddCart = false
                newList.remove(newProduct)
                Log.d("TTTT", newList.size.toString())
                plus.visibility = View.VISIBLE
                minus.visibility = View.VISIBLE
            } else {
                btnAddToCart.text = "Added Cart"
                btnAddToCart.setBackgroundResource(R.drawable.bg_added_cart)
                isAddCart = true
                newList.add(newProduct)
                Log.d("TTTT", newList.size.toString())
                plus.visibility = View.GONE
                minus.visibility = View.GONE
            }


        }


    }


}
