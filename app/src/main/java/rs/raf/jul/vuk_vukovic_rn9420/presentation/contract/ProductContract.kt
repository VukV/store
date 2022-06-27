package rs.raf.jul.vuk_vukovic_rn9420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.CategoryState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.ProductState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.SingleProductState

interface ProductContract {

    interface ViewModel{

        val productState: LiveData<ProductState>
        val categoryState: LiveData<CategoryState>
        val singleProductState: LiveData<SingleProductState>

        fun fetchAll()
        fun getAll()

        fun getAllBySearch(searchTag: String)
        fun getAllByCategory(category: String)

        fun fetchCategories()
        fun getCategories()

        fun fetchSingleProduct(productId: Int)
        fun clearProduct()
    }
}