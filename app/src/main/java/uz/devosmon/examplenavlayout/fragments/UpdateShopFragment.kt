package uz.devosmon.examplenavlayout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.devosmon.examplenavlayout.R
import uz.devosmon.examplenavlayout.models.ShopProduct


class UpdateShopFragment : Fragment() {

    lateinit var shopProduct: ShopProduct


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_update_shop, container, false)


        return root

    }


}