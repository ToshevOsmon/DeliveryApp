package uz.devosmon.examplenavlayout.tablayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.devosmon.examplenavlayout.tablayout.tab_fragments.ProductsFragment
import uz.devosmon.examplenavlayout.tablayout.tab_fragments.ShopsFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

       return when (position) {
            0 -> {
                ProductsFragment()

            }
            1 -> {
                ShopsFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}