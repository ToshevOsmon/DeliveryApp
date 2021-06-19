package uz.devosmon.examplenavlayout.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.adapters.ShopingCartAdapter
import uz.devosmon.examplenavlayout.models.ShopProduct
import uz.devosmon.examplenavlayout.viewmodel.ShopProductViewModel


class AddCartFragment : Fragment() {

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

        rv = root.findViewById(R.id.shoppingCartRv)
        totalPerice = root.findViewById(R.id.totalPerice)

        Log.d("TTTT", "shoping list fragminga keldi")
        shopProductViewModel = ViewModelProviders.of(this).get(ShopProductViewModel::class.java)
        shopProductViewModel.getAllShopProductsObservce().observe(this, Observer {

            Log.d("TTT", it.toString())
            if ((it as ArrayList<ShopProduct>).isEmpty()) {
                Toast.makeText(activity, "List Bosh", Toast.LENGTH_LONG).show()
            } else {
                shoppingCartAdapter.setListData(it)
                for (product in it) {
                    summa = summa + product.perice
                }
                totalPerice.text = "Total: " + summa.toString() + ".00$"
            }


            shoppingCartAdapter.notifyDataSetChanged()
        })

        rv.layoutManager = LinearLayoutManager(activity)
        shoppingCartAdapter = ShopingCartAdapter()
        rv.adapter = shoppingCartAdapter
        Log.d("TTTT", "list ekranga chiqdi")
        rv.hasFixedSize()
        shoppingCartAdapter.notifyDataSetChanged()



        return root
    }

}
