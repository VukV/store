package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment(R.layout.fragment_discover) {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()
        initRecycler()
        initListeners()
    }

    private fun initSpinner(){

    }

    private fun initRecycler(){
        binding.productsRecycler.layoutManager = LinearLayoutManager(context)

    }

    private fun initListeners(){

    }
}