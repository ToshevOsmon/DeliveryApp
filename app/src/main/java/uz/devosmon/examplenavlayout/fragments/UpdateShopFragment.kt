package uz.devosmon.examplenavlayout.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.makeramen.roundedimageview.RoundedImageView
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.models.ShopProduct
import uz.devosmon.examplenavlayout.viewmodel.ShopProductViewModel


class UpdateShopFragment : Fragment() {

    lateinit var shopProduct: ShopProduct
    lateinit var shopProductViewModel: ShopProductViewModel

    lateinit var imgItem: RoundedImageView
    lateinit var productName: TextView
    lateinit var minus: Button
    lateinit var plus: Button
    lateinit var countProduct: TextView
    lateinit var productPrice: TextView
    lateinit var btnUpdateToCart: Button
    lateinit var productDesc: TextView
    var count = 0
    var sum = 0

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_update_shop, container, false)

        imgItem = root.findViewById(R.id.imgItem)
        productName = root.findViewById(R.id.productName)
        minus = root.findViewById(R.id.minus)
        plus = root.findViewById(R.id.plus)
        countProduct = root.findViewById(R.id.countProduct)
        productPrice = root.findViewById(R.id.productPrice)
        productDesc = root.findViewById(R.id.productDesc)
        btnUpdateToCart = root.findViewById(R.id.btnUpdateToCart)

        //bundle fragmentda ma'lumotni olib o'tish uchun
        val bundle: Bundle = this.arguments!!
        val product = bundle.getSerializable("update") as ShopProduct

        productName.text = product.name
        imgItem.setImageResource(product.image)
        countProduct.text = product.count.toString()
        count = product.count
        productPrice.text = product.perice.toString() + ".00$"

        productDesc.text = product.desc


        minus.setOnClickListener {

            if (count > 1) {
                count--
                sum = product.perice * count

                countProduct.text = count.toString()
                productPrice.text = sum.toString() + ".00$"
            } else {
                count = 1
                sum = product.perice

                countProduct.text = count.toString()
                productPrice.text = sum.toString() + ".00$"
            }

        }
        plus.setOnClickListener {

            count++
            sum = product.perice * count

            countProduct.text = count.toString()
            productPrice.text = sum.toString() + ".00$"

        }



        btnUpdateToCart.setOnClickListener {

            Log.d("TTTT","Count: ${count} Sum: ${sum}")

            shopProduct = ShopProduct(product.id, product.name, product.image, sum, product.desc, count)

            shopProductViewModel = ViewModelProviders.of(this).get(ShopProductViewModel::class.java)
            shopProductViewModel.updateShopProduct(shopProduct)
            Toast.makeText(activity, "Update Product", Toast.LENGTH_SHORT).show()

            btnUpdateToCart.setBackgroundResource(R.drawable.bg_added_cart)

            val fragment = AddCartFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.container,fragment)?.commit()

        }



        return root

    }


}