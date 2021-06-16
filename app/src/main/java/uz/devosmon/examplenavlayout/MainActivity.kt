package uz.devosmon.examplenavlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import uz.devosmon.examplenavlayout.fragments.AddCartFragment
import uz.devosmon.examplenavlayout.fragments.MenuFragment
import uz.devosmon.examplenavlayout.fragments.PersonFragment
import uz.devosmon.examplenavlayout.fragments.SearchFragment
import uz.devosmon.examplenavlayout.tablayout.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {

    private val menuFragment = MenuFragment()
    private val searchFragment = SearchFragment()
    private val addCartFragment = AddCartFragment()
    private val personFragment = PersonFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val handler = Handler();
        handler.post(object : Runnable{
            override fun run() {

                replaceFragment(menuFragment)

            }
        })



        bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu ->{
                    replaceFragment(menuFragment)
                }
                R.id.search -> {
                    replaceFragment(searchFragment)
                }
                R.id.addShopCart -> {
                    replaceFragment(addCartFragment)
                }
                R.id.person ->{
                    replaceFragment(personFragment)
                }
            }
            true
        }




    }
    private fun replaceFragment(fragment: Fragment) {

        if (fragment !=null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
        else{
            Toast.makeText(this,"Fragment Null",Toast.LENGTH_SHORT).show()
        }
    }

}
