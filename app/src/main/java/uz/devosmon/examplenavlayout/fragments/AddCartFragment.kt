package uz.devosmon.examplenavlayout.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import uz.devosmon.examplenavlayout.PaypalActivity
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.adapters.OnItemClickListener
import uz.devosmon.examplenavlayout.adapters.OnItemDeleteListener
import uz.devosmon.examplenavlayout.adapters.ShopingCartAdapter
import uz.devosmon.examplenavlayout.models.ShopProduct
import uz.devosmon.examplenavlayout.paypal.Config
import uz.devosmon.examplenavlayout.viewmodel.ShopProductViewModel
import java.math.BigDecimal


class AddCartFragment : Fragment() {

    lateinit var paypalId: Button
    lateinit var rv: RecyclerView
    lateinit var totalPerice: TextView
    lateinit var shoppingCartAdapter: ShopingCartAdapter
    lateinit var shopProductViewModel: ShopProductViewModel
    var summa = 0

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add_cart, container, false)

        paypalId = root.findViewById(R.id.paypalId)
        rv = root.findViewById(R.id.shoppingCartRv)
        totalPerice = root.findViewById(R.id.totalPerice)

        shopProductViewModel = ViewModelProviders.of(this).get(ShopProductViewModel::class.java)
        shopProductViewModel.getAllShopProductsObservce().observe(this, Observer {

            Log.d("TTT", it.toString())

            if ((it as ArrayList<ShopProduct>).isEmpty()) {

                Toast.makeText(activity, "List Bosh", Toast.LENGTH_LONG).show()
                shoppingCartAdapter.setListData(it)
                totalPerice.text = "0.00$"

            } else {

                shoppingCartAdapter.setListData(it)

                for (product in it) {

                    summa = summa + product.perice

                }
                totalPerice.text = "Total: " + summa.toString() + ".00$"
                shoppingCartAdapter.notifyDataSetChanged()
            }


            shoppingCartAdapter.notifyDataSetChanged()
        })

        rv.layoutManager = LinearLayoutManager(activity)

        shoppingCartAdapter = ShopingCartAdapter(object : OnItemClickListener {
            override fun onClick(shopProduct: ShopProduct) {

                // send data from fragment to fragment

                val bundle = Bundle()
                bundle.putSerializable("update", shopProduct)

                val fragment = UpdateShopFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.commit()

            }
        }, object : OnItemDeleteListener {
            override fun onClick(shopProduct: ShopProduct) {

                shopProductViewModel.deleteShopProduct(shopProduct)

                summa = summa - shopProduct.perice
                totalPerice.text = "Total: " + summa.toString() + ".00$"

                shoppingCartAdapter.notifyDataSetChanged()

            }
        })


        rv.adapter = shoppingCartAdapter
        rv.hasFixedSize()
        shoppingCartAdapter.notifyDataSetChanged()



        paypalId.setOnClickListener {

            var intent = Intent(activity, PaypalActivity::class.java)
            intent.putExtra("perice", summa)
            startActivity(intent)

        }

        return root

    }


}
