package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentMainBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.viewpager.PagerAdapter

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
    }

    private fun initViewPager(){
        binding.viewPager.adapter = PagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}