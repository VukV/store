package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.viewpager

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments.DiscoverFragment
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments.ProfileFragment

class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val FRAGMENT_COUNT = 2
        const val DISCOVER_FRAGMENT = 0
        const val PROFILE_FRAGMENT = 1
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            DISCOVER_FRAGMENT -> DiscoverFragment()
            else -> ProfileFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            DISCOVER_FRAGMENT -> "Discover"
            else -> "Profile"
        }
    }

    private fun getTitleFromR(id: Int): String{
        return Resources.getSystem().getString(id)
    }
}