package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product

class ProductAdapter (
    productDiffCallback: ProductDiffCallback,
    private val onProductClicked: (Product) -> Unit
) : ListAdapter<Product, ProductViewHolder>(productDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}