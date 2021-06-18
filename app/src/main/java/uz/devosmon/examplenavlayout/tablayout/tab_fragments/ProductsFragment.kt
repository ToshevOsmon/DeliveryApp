package uz.devosmon.examplenavlayout.tablayout.tab_fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.devosmon.examplenavlayout.ProductActivity

import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.adapters.CategoryAdapter
import uz.devosmon.examplenavlayout.adapters.OnClickListener
import uz.devosmon.examplenavlayout.adapters.ShopsAdapter
import uz.devosmon.examplenavlayout.adapters.TopProductAdapter
import uz.devosmon.examplenavlayout.models.Category
import uz.devosmon.examplenavlayout.models.Product
import uz.devosmon.examplenavlayout.models.Shops


class ProductsFragment : Fragment() {
    lateinit var adapter: TopProductAdapter
    var lists: List<Product> = ArrayList()
    var productRv: RecyclerView? = null

    lateinit var categoryAdapter: CategoryAdapter
    var categoryList: List<Category> = ArrayList()
    var categoryRv: RecyclerView? = null

    lateinit var shopAdapter: ShopsAdapter
    var shopsList: List<Shops> = ArrayList()
    var shopsRv: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_products, container, false)
        productRv = root.findViewById(R.id.productsRv)
        categoryRv = root.findViewById(R.id.categoryRv)
        shopsRv = root.findViewById(R.id.shopsRv)

        loadData()
        loadCategoryData()
        loadShopsData()

        laodCategoryRv()
        loadShopsRv()

        productRv?.layoutManager =
            LinearLayoutManager(activity)

        adapter = TopProductAdapter(object : OnClickListener {
            override fun onClick(product: Product) {

                val intent = Intent(activity, ProductActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)


            }
        })
        adapter.setData(lists)
        productRv?.adapter = adapter
        productRv?.setHasFixedSize(true)
        adapter.notifyDataSetChanged()


        return root
    }

    private fun loadShopsRv() {

        shopsRv?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        shopAdapter = ShopsAdapter(shopsList)
        shopsRv?.adapter = shopAdapter
        shopsRv?.setHasFixedSize(true)
        shopAdapter.notifyDataSetChanged()

    }

    private fun loadShopsData() {
        shopsList = ArrayList()
        (shopsList as ArrayList<Shops>).add(Shops(1, "Havas", R.drawable.ic_headset))
        (shopsList as ArrayList<Shops>).add(Shops(2, "Sadab", R.drawable.ic_add_shopping_cart))
        (shopsList as ArrayList<Shops>).add(Shops(3, "Shirin", R.drawable.ic_phone_android))
        (shopsList as ArrayList<Shops>).add(Shops(4, "Abu Sahiy", R.drawable.ic_watch))
        (shopsList as ArrayList<Shops>).add(Shops(5, "Olcha.uz", R.drawable.ic_new))
        (shopsList as ArrayList<Shops>).add(Shops(6, "Abad.uz", R.drawable.ic_computer))
        (shopsList as ArrayList<Shops>).add(Shops(7, "Karzinka", R.drawable.ic_laptop))
        (shopsList as ArrayList<Shops>).add(Shops(8, "Assaxiy.uz", R.drawable.ic_tv))

    }

    private fun laodCategoryRv() {
        categoryRv?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        categoryAdapter = CategoryAdapter(categoryList)
        categoryRv?.adapter = categoryAdapter
        categoryRv?.setHasFixedSize(true)
        categoryAdapter.notifyDataSetChanged()


    }

    private fun loadCategoryData() {

        categoryList = ArrayList()

        (categoryList as ArrayList<Category>).add(Category(1, "News", R.drawable.ic_new))
        (categoryList as ArrayList<Category>).add(
            Category(
                2,
                "Smartphone",
                R.drawable.ic_phone_android
            )
        )
        (categoryList as ArrayList<Category>).add(
            Category(
                3,
                "Notebook",
                R.drawable.ic_laptop
            )
        )
        (categoryList as ArrayList<Category>).add(
            Category(
                4,
                "Accessories",
                R.drawable.ic_headset
            )
        )
        (categoryList as ArrayList<Category>).add(Category(5, "PC", R.drawable.ic_computer))
        (categoryList as ArrayList<Category>).add(Category(6, "Watch", R.drawable.ic_watch))
        (categoryList as ArrayList<Category>).add(Category(7, "Game", R.drawable.ic_videogame))
        (categoryList as ArrayList<Category>).add(Category(8, "TV", R.drawable.ic_tv))
        (categoryList as ArrayList<Category>).add(
            Category(
                9,
                "Others",
                R.drawable.ic_devices_other
            )
        )

    }

    private fun loadData() {

        lists = ArrayList()

        (lists as ArrayList<Product>).add(Product(1, "Watch", R.drawable.watch3, 200, "soat",1))
        (lists as ArrayList<Product>).add(
            Product(
                2,
                "Laptop HP",
                R.drawable.laptop2,
                2000,
                "notebook HP",1
            )
        )
        (lists as ArrayList<Product>).add(
            Product(
                3,
                "Redme 5 Pro",
                R.drawable.redme5pro,
                240,
                "Redme 5 Pro",1
            )
        )
        (lists as ArrayList<Product>).add(
            Product(
                4,
                "Samsung Note 20",
                R.drawable.gallaxynote20,
                200,
                "Samsung gallaxy note 20",1
            )
        )
        (lists as ArrayList<Product>).add(
            Product(
                5,
                "Watch Armany",
                R.drawable.watch2,
                200,
                "Armaniy Soat",1
            )
        )
        (lists as ArrayList<Product>).add(
            Product(
                6,
                "Quloqchin",
                R.drawable.quloqchin,
                230,
                "Quloqchin",1
            )
        )
        val product = Product(7, "Bluetooth", R.drawable.bluetooth, 22, "Bluetooth li quloqchin ",1)
        (lists as ArrayList<Product>).add(product)
        (lists as ArrayList<Product>).add(
            Product(
                8,
                "Quloqchin Stok",
                R.drawable.quloqchinstok,
                230,
                "Quloqchin",1
            )
        )
        (lists as ArrayList<Product>).add(
            Product(
                9,
                "Iphone 12 Pro Max",
                R.drawable.iphone12promax,
                230,
                "Iphone 12 Pro Max",1
            )
        )
    }
}
