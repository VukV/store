package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.productimage

import androidx.recyclerview.widget.DiffUtil

class ProductImageDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}