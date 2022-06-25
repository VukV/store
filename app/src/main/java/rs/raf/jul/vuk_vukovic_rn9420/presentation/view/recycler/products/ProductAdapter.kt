package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ItemProductBinding

class ProductAdapter (
    productDiffCallback: ProductDiffCallback,
    private val onProductClicked: (Product) -> Unit
) : ListAdapter<Product, ProductViewHolder>(productDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemProductBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemProductBinding){
            onProductClicked.invoke(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}