package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.cart

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProduct

class CartDiffCallback: DiffUtil.ItemCallback<CartProduct>() {

    override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem.id == newItem.id && oldItem.username == newItem.username
    }

    override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem.title == newItem.title
                && oldItem.price == newItem.price
                && oldItem.amount == newItem.amount
    }
}