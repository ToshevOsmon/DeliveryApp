package uz.devosmon.examplenavlayout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_menu.*

import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.tablayout.adapter.FragmentAdapter


class MenuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_menu, container, false)


return root

}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter =
            FragmentAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        view_pager.isSaveEnabled = false
        view_pager?.adapter = adapter

        TabLayoutMediator(tabLayout, view_pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Products"
                }
                1 -> {
                    tab.text = "Shops"
                }
            }
        }.attach()
    }

}
