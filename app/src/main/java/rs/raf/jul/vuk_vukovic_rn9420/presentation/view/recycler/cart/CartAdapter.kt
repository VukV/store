package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProduct
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ItemProductCartBinding

class CartAdapter(
    cartDiffCallback: CartDiffCallback,
    private val onCartProductClicked: (CartProduct) -> Unit
) : ListAdapter<CartProduct, CartViewHolder>(cartDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemProductCartBinding = ItemProductCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(itemProductCartBinding){
            onCartProductClicked.invoke(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}