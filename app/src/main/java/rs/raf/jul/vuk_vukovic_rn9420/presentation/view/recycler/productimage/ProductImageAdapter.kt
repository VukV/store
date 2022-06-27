package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.productimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ItemProductImageBinding

class ProductImageAdapter : ListAdapter<String, ProductImageViewHolder>(ProductImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val itemProductImageBinding = ItemProductImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductImageViewHolder(itemProductImageBinding)
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}