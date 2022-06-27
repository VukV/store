package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentCartBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.CartContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.cart.CartAdapter
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.cart.CartDiffCallback
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.CartViewModel

class CartFragment : Fragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CartAdapter
    private val cartViewModel: CartContract.ViewModel by sharedViewModel<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        //TODO
    }

    private fun initListeners() {
        //TODO
    }

    private fun initRecycler() {
        binding.cartRecycler.layoutManager = LinearLayoutManager(context)
        adapter = CartAdapter(CartDiffCallback()){
            //TODO remove from cart
        }
        binding.cartRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}