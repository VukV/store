package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentDiscoverBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.ProductContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.CategoryState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.ProductState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products.ProductAdapter
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products.ProductDiffCallback
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.ProductViewModel

class DiscoverFragment : Fragment(R.layout.fragment_discover) {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ProductAdapter
    private val productViewModel: ProductContract.ViewModel by sharedViewModel<ProductViewModel>()

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

        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initSpinner(categories: List<String>){
        val spinnerList: MutableList<String> = categories.toMutableList()
        spinnerList.add(0, "Categories")

        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerList)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.categorySpinner.adapter = categoryAdapter

    }

    private fun initRecycler(){
        binding.productsRecycler.layoutManager = LinearLayoutManager(context)
        adapter = ProductAdapter(ProductDiffCallback()){
            startSingleProductFragment(it.id)
        }
        binding.productsRecycler.adapter = adapter
    }

    private fun initListeners(){
        binding.searchEditText.doAfterTextChanged {
            val searchTag = it.toString()

            if (searchTag.isNotBlank()){
                clearSpinner()
                productViewModel.getAllBySearch(searchTag)
            }
        }

        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val category = binding.categorySpinner.adapter.getItem(position)

                if(category.toString() != "Categories"){
                    clearSearch()
                    productViewModel.getAllByCategory(category.toString())
                }
            }
        }
    }

    private fun initObservers(){
        productViewModel.productState.observe(viewLifecycleOwner){
            renderState(it)
        }

        productViewModel.categoryState.observe(viewLifecycleOwner){
            when(it){
                is CategoryState.Success -> {
                    initSpinner(it.categories)
                }
                else -> {}
            }
        }

        productViewModel.getCategories()
        productViewModel.fetchCategories()

        productViewModel.getAll()
        productViewModel.fetchAll()
    }

    private fun renderState(state: ProductState){
        when(state){
            is ProductState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.products)
            }
            is ProductState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ProductState.DataFetched -> {
                showLoadingState(false)
            }
            is ProductState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.progressBar.isVisible = loading
        binding.searchEditText.isEnabled = !loading
        binding.categorySpinner.isEnabled = !loading
    }

    private fun startSingleProductFragment(productId: Int){
        clearSearch()
        clearSpinner()

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.addToBackStack(null)
        transaction?.replace(R.id.fragmentContainerMain, ProductFragment(productId))
        transaction?.commit()
    }

    private fun clearSearch(){
        binding.searchEditText.text.clear()
    }

    private fun clearSpinner(){
        binding.categorySpinner.setSelection(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}