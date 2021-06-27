package uz.devosmon.examplenavlayout.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.devosmon.examplenavlayout.ProductActivity
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.adapters.OnClickItemListener
import uz.devosmon.examplenavlayout.adapters.ProductAdapter
import uz.devosmon.examplenavlayout.models.Product


class SearchFragment : Fragment() {

    lateinit var search: SearchView
    lateinit var adapter: ProductAdapter
    var lists = ArrayList<Product>()
    var productRv: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_search, container, false)

        productRv = root.findViewById(R.id.searchProductRv)
        search = root.findViewById(R.id.search)

        loadData()

        productRv?.layoutManager = LinearLayoutManager(activity)

        adapter = ProductAdapter(object : OnClickItemListener {
            override fun onClick(product: Product) {
                val intent = Intent(activity, ProductActivity::class.java)
                intent.putExtra("search", product)
                startActivity(intent)

            }
        });
        adapter.setData(lists)
        productRv?.adapter = adapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true

            }
        })

        return root
    }

    private fun loadData() {

        lists.add(Product(1, "Watch", R.drawable.watch3, 200, "soat", 1))
        lists.add(
            Product(
                2,
                "Laptop HP",
                R.drawable.laptop2,
                2000,
                "notebook HP", 1
            )
        )
        lists.add(
            Product(
                3,
                "Redme 5 Pro",
                R.drawable.redme5pro,
                240,
                "Redme 5 Pro", 1
            )
        )
        lists.add(
            Product(
                4,
                "Samsung Note 20",
                R.drawable.gallaxynote20,
                200,
                "Samsung gallaxy note 20", 1
            )
        )
        lists.add(
            Product(
                5,
                "Watch Armany",
                R.drawable.watch2,
                200,
                "Armaniy Soat", 1
            )
        )
        lists.add(
            Product(
                6,
                "Quloqchin",
                R.drawable.quloqchin,
                230,
                "Quloqchin", 1
            )
        )
        val product =
            Product(7, "Bluetooth", R.drawable.bluetooth, 22, "Bluetooth li quloqchin ", 1)
        lists.add(product)
        lists.add(
            Product(
                8,
                "Quloqchin Stok",
                R.drawable.quloqchinstok,
                230,
                "Quloqchin", 1
            )
        )
        lists.add(
            Product(
                9,
                "Iphone 12 Pro Max",
                R.drawable.iphone12promax,
                230,
                "Iphone 12 Pro Max", 1
            )
        )
    }

}
