package uz.devosmon.examplenavlayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_category_layout.view.*
import kotlinx.android.synthetic.main.item_shop_layout.view.*
import kotlinx.android.synthetic.main.item_shopping_cart_layout.view.*
import kotlinx.android.synthetic.main.item_top_product_layout.view.*
import kotlinx.android.synthetic.main.item_top_product_layout.view.imageProduct
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.models.Category
import uz.devosmon.examplenavlayout.models.Product
import uz.devosmon.examplenavlayout.models.ShopProduct
import uz.devosmon.examplenavlayout.models.Shops

class ShopingCartAdapter(
    val listener: OnItemClickListener,
    val deleteListener: OnItemDeleteListener
) :
    RecyclerView.Adapter<ShopingCartAdapter.ViewHolder>() {

    var lists = ArrayList<ShopProduct>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    fun setListData(data: ArrayList<ShopProduct>) {
        this.lists = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_shopping_cart_layout, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.imageShoppingProduct.setImageResource(lists[position].image)
        holder.itemView.shoppingProductName.text = lists[position].name
        holder.itemView.shoppingCount.text = "Count: " + lists[position].count.toString()
        holder.itemView.shoppingPerice.text =
            "Perice: " + lists[position].perice.toString() + ".00$"

        holder.itemView.setOnClickListener {

            listener.onClick(lists[position])

        }
        holder.itemView.deleteProductId.setOnClickListener {

            deleteListener.onClick(lists[position])
        }


    }

    override fun getItemCount(): Int = lists.count()
}

interface OnItemClickListener {

    fun onClick(shopProduct: ShopProduct)

}

interface OnItemDeleteListener {

    fun onClick(shopProduct: ShopProduct)

}
