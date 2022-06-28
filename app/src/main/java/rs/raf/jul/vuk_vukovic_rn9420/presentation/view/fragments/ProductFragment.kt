package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.SingleProduct
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentProductBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.CartContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.ProductContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.AddToCartState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.CartState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.ProductState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.SingleProductState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.productimage.ProductImageAdapter
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products.ProductViewHolder
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.CartViewModel
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.ProductViewModel

class ProductFragment(private val productId: Int) : Fragment(R.layout.fragment_product) {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ProductImageAdapter
    private val productViewModel: ProductContract.ViewModel by sharedViewModel<ProductViewModel>()
    private val cartViewModel: CartContract.ViewModel by sharedViewModel<CartViewModel>()

    companion object{
        private const val red = "#CA0000"
        private const val orange = "#F17100"
        private const val yellow = "#FFC61A"
        private const val lightGreen = "#50E175"
        private const val green = "#228826"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initRecycler(){
        binding.imagesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ProductImageAdapter()
        binding.imagesRecyclerView.adapter = adapter
    }

    private fun initListeners(){
        binding.addToCartButton.setOnClickListener {
            val title: String = binding.titleText.text.toString()
            val price: Double = binding.priceProductText.text.toString().toDouble()
            cartViewModel.addToCart(id = productId, title = title, price = price)
        }
    }

    private fun initObservers(){
        productViewModel.singleProductState.observe(viewLifecycleOwner){
            renderState(it)
        }

        cartViewModel.addToCartState.observe(viewLifecycleOwner){
            renderCartState(it)
        }

        productViewModel.fetchSingleProduct(productId)
    }

    private fun renderState(state: SingleProductState){
        when(state){
            is SingleProductState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.product.images)
                fillProductInfo(state.product)
            }
            is SingleProductState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is SingleProductState.NoData -> showLoadingState(true)
        }
    }

    private fun renderCartState(state: AddToCartState){
        when(state){
            is AddToCartState.Added -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
            is AddToCartState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> return
        }
    }

    private fun fillProductInfo(product: SingleProduct){
        binding.titleText.text = product.title
        binding.brandProductText.text = product.brand
        binding.descriptionProductText.text = product.description
        binding.categoryProductText.text = product.category
        binding.discountProductText.text = "${product.discountPercentage} %"
        binding.priceProductText.text = product.price.toString()

        binding.ratingProductText.text = product.rating.toString()
        setRatingColor(product.rating)
    }

    private fun showLoadingState(loading: Boolean) {
        binding.productProgressBar.isVisible = loading
        binding.addToCartButton.isEnabled = !loading
    }

    private fun setRatingColor(rating: Double){
        if(rating <= 4.2){
            binding.ratingProductText.setTextColor(Color.parseColor(red))
        }
        else if(rating > 4.2 && rating <= 4.4){
            binding.ratingProductText.setTextColor(Color.parseColor(orange))
        }
        else if(rating > 4.4 && rating <= 4.6){
            binding.ratingProductText.setTextColor(Color.parseColor(yellow))
        }
        else if(rating > 4.6 && rating <= 4.8){
            binding.ratingProductText.setTextColor(Color.parseColor(lightGreen))
        }
        else{
            binding.ratingProductText.setTextColor(Color.parseColor(green))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        productViewModel.clearProduct()
        cartViewModel.idle()
    }
}