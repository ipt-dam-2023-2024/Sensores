package ipt.dam.fragmentosversaob

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ipt.dam.fragmentosversaob.Fragmentos.*


class MyViewPagerAdapter: FragmentStateAdapter {

    private lateinit var fragmentActivity: FragmentActivity

    constructor(fragmentActivity: FragmentActivity):super(fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return Fragmento1(fragmentActivity)
            1 -> return Fragmento2()
            2 -> return Fragmento3(fragmentActivity)
            else -> return Fragmento1(fragmentActivity)
        }
    }

}

