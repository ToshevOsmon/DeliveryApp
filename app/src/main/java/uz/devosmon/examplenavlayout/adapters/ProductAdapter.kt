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

class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {

    var lists = ArrayList<Product>()
    var filterLists = ArrayList<Product>()

init {
    filterLists = lists
}

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_product_layout, parent, false)
        )

    }

    fun setData(arrData: List<Product>) {
        lists = arrData as ArrayList<Product>
        filterLists = lists
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.imageProduct.setImageResource(filterLists[position].image)
        holder.itemView.productName.text = filterLists[position].name

    }

    override fun getItemCount(): Int = filterLists.count()

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {

                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    filterLists = lists
                } else {
                    var resultList = ArrayList<Product>()
                    for (raw in lists) {

                        if (raw.name.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            resultList.add(raw)
                        }
                    }
                    filterLists = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterLists
                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

                filterLists = p1?.values as ArrayList<Product>
                notifyDataSetChanged()

            }

        }

    }
}