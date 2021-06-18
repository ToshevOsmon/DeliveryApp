package uz.devosmon.examplenavlayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_top_product_layout.view.*
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.models.Product
import java.util.*
import kotlin.collections.ArrayList


interface OnClickListener {
    fun onClick(product: Product)
}

class TopProductAdapter(val listener: OnClickListener) :
    RecyclerView.Adapter<TopProductAdapter.ViewHolder>() {

    var lists = ArrayList<Product>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_product_layout, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.imageProduct.setImageResource(lists[position].image)
        holder.itemView.productName.text = lists[position].name
        holder.itemView.rootView.setOnClickListener {
            listener.onClick(lists[position])
        }

    }

    override fun getItemCount(): Int = lists.count()


    fun setData(arrData: List<Product>) {
        lists = arrData as ArrayList<Product>
    }

}