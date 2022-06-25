package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.description == newItem.description &&
                oldItem.category == newItem.category &&
                oldItem.price == newItem.price &&
                oldItem.rating == newItem.rating
    }
}