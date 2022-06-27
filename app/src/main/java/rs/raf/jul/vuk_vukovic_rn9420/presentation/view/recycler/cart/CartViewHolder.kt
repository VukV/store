package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.cart

import androidx.recyclerview.widget.RecyclerView
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProduct
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ItemProductCartBinding

class CartViewHolder(
    private val itemProductCartBinding: ItemProductCartBinding,
    onCartProductClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemProductCartBinding.root) {

    init {
        itemProductCartBinding.removeFromCartButton.setOnClickListener {
            onCartProductClicked.invoke(bindingAdapterPosition)
        }
    }

    fun bind(cartProduct: CartProduct){
        itemProductCartBinding.titleCartText.text = cartProduct.title
        itemProductCartBinding.priceCartText.text = cartProduct.price.toString()
        itemProductCartBinding.amountCartText.text = cartProduct.amount.toString()
    }
}