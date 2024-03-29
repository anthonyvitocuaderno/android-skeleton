package com.vitocuaderno.skeleton.features.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vitocuaderno.skeleton.features.main.dummy.DummyFragment
import com.vitocuaderno.skeleton.features.main.users.UsersFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 5

    private val titles = arrayOf(
        "Dummy",
        "Users",
        "Dummy",
        "Dummy",
        "Menu"
    )

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            1 -> {
                UsersFragment()
            } else -> {
                DummyFragment()
            }
        }
        return fragment
    }

    fun getTitle(position: Int): String {
        return titles[position]
    }
}
