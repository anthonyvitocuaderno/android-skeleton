package com.vitocuaderno.skeleton.features.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vitocuaderno.skeleton.features.main.calendar.week.WeekCalendarFragment
import com.vitocuaderno.skeleton.features.main.dummy.DummyFragment
import com.vitocuaderno.skeleton.features.main.todos.TodoListFragment
import com.vitocuaderno.skeleton.features.main.users.UsersFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 5

    private val titles = arrayOf<String>(
        "ToDo",
        "Users",
        "Week",
        "Dummy",
        "Menu"
    )

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> {
                TodoListFragment()
            }
            1 -> {
                UsersFragment()
            }
            2 -> {
                WeekCalendarFragment()
            }
            else -> {
                DummyFragment()
            }
        }
        return fragment
    }

    fun getTitle(position: Int): String {
        return titles[position]
    }
}
