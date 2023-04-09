package com.devsimiyu.harrypotter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.devsimiyu.harrypotter.utils.HomePagerAdapter
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)

        setupBackNav()
        setupTabs()

        return view
    }

    private fun setupBackNav(): Unit {
        val button: Button = view.findViewById(R.id.btnShowDetails) as Button

        button.setOnClickListener{view ->
            view.findNavController().navigate(R.id.detailFragment)
        }
    }

    private fun setupTabs(): Unit {
        tabLayout = view.findViewById(R.id.home_tab_layout)
        viewPager2 = view.findViewById(R.id.home_view_pager)
        homePagerAdapter = HomePagerAdapter(requireActivity())
        viewPager2.adapter = homePagerAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Do something when tab is unselected
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do something when tab is reselected
            }
        })

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }
        })
    }
}