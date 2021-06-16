package uz.devosmon.examplenavlayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_top_product_layout.view.*
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.models.Product

class TopProductAdapter(val lists:List<Product>) : RecyclerView.Adapter<TopProductAdapter.ViewHolder>() {


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

    }

    override fun getItemCount(): Int = lists.count()
}