package uz.devosmon.examplenavlayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_category_layout.view.*
import kotlinx.android.synthetic.main.item_top_product_layout.view.*
import kotlinx.android.synthetic.main.item_top_product_layout.view.imageProduct
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.models.Category
import uz.devosmon.examplenavlayout.models.Product

class CategoryAdapter(val lists:List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_layout, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.imageProduct.setImageResource(lists[position].imageCategory)
        holder.itemView.categoryName.text = lists[position].nameCategory

    }

    override fun getItemCount(): Int = lists.count()
}