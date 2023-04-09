package com.devsimiyu.harrypotter.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devsimiyu.harrypotter.StaffFragment
import com.devsimiyu.harrypotter.StudentFragment

class HomePagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StudentFragment()
            1 -> StaffFragment()
            else -> StudentFragment()
        }
    }
}