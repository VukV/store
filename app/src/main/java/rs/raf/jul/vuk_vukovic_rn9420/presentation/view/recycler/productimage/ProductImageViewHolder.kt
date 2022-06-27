package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.productimage

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ItemProductImageBinding

class ProductImageViewHolder(private val itemProductImageBinding: ItemProductImageBinding): RecyclerView.ViewHolder(itemProductImageBinding.root) {

    fun bind(url: String){
        Picasso
            .get()
            .load(url)
            .resize(500, 500)
            .centerCrop()
            .into(itemProductImageBinding.productImageViewItem)
    }
}