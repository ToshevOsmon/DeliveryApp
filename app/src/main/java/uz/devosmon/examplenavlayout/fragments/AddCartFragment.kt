package uz.devosmon.examplenavlayout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.devosmon.examplenavlayout.ProductActivity

import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.adapters.ProductAdapter
import uz.devosmon.examplenavlayout.adapters.ShopingCartAdapter
import uz.devosmon.examplenavlayout.models.Product


class AddCartFragment : Fragment() {
    lateinit var productActivity: ProductActivity
    lateinit var lists: ArrayList<Product>
    lateinit var adapter: ShopingCartAdapter
    lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add_cart, container, false)

        rv = root.findViewById(R.id.shoppingCartRv)

        productActivity = ProductActivity()
        lists = productActivity.newList

        rv.layoutManager = LinearLayoutManager(activity)
        adapter = ShopingCartAdapter(lists)
        rv.adapter = adapter
        rv.setHasFixedSize(false)
        adapter.notifyDataSetChanged()


        return root
    }

}
