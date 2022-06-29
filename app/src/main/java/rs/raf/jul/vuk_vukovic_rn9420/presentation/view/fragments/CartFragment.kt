package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentCartBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.CartContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.CartState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.PriceState
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

    private fun initListeners() {
        binding.payButton.setOnClickListener {
            cartViewModel.removeAllFromCart()
        }
    }

    private fun initRecycler() {
        binding.cartRecycler.layoutManager = LinearLayoutManager(context)
        adapter = CartAdapter(CartDiffCallback()){
            cartViewModel.removeFromCart(it.id)
        }
        binding.cartRecycler.adapter = adapter
    }

    private fun initObservers() {
        cartViewModel.cartState.observe(viewLifecycleOwner){
            renderState(it)
        }

        cartViewModel.priceState.observe(viewLifecycleOwner){
            renderTotalPrice(it)
        }

        cartViewModel.getAllFromCart()
        cartViewModel.getTotalPrice()
    }

    private fun renderState(state: CartState){
        when(state){
            is CartState.Idle -> return
            is CartState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is CartState.Emptied -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
            is CartState.Success -> {
                adapter.submitList(state.cart)
            }
        }
    }

    private fun renderTotalPrice(state: PriceState){
        when(state){
            is PriceState.Idle -> return
            is PriceState.Error -> Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            is PriceState.Success -> {
                val buttonText = "${getString(R.string.pay)} ${state.totalPrice}"
                binding.payButton.text = buttonText
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        cartViewModel.idle()
    }
}