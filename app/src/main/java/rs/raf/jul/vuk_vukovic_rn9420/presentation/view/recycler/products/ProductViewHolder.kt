package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ItemProductBinding

class ProductViewHolder(
    private val itemProductBinding: ItemProductBinding,
    onProductClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemProductBinding.root) {

    companion object{
        private const val red = 0xCA0000
        private const val orange = 0xF17100
        private const val yellow = 0xFFEB3B
        private const val lightGreen = 0x31C657
        private const val green = 0x08B123
    }

    init {
        itemProductBinding.productHolder.setOnClickListener {
            onProductClicked.invoke(bindingAdapterPosition)
        }
    }

    fun bind(product: Product){
        itemProductBinding.titleText.text = product.title
        itemProductBinding.priceText.text = product.price.toString()

        itemProductBinding.ratingText.text = product.rating.toString()
        setRatingColor(product.rating)

        Picasso.get().load(product.thumbnail).into(itemProductBinding.productImageView)
    }

    private fun setRatingColor(rating: Double){
        if(rating <= 4.2){
            itemProductBinding.ratingText.setTextColor(red)
            itemProductBinding.ratingIcon.drawable.setTint(red)
        }
        else if(rating <= 4.4){
            itemProductBinding.ratingText.setTextColor(orange)
            itemProductBinding.ratingIcon.drawable.setTint(orange)
        }
        else if(rating <= 4.6){
            itemProductBinding.ratingText.setTextColor(yellow)
            itemProductBinding.ratingIcon.drawable.setTint(yellow)
        }
        else if(rating <= 4.8){
            itemProductBinding.ratingText.setTextColor(lightGreen)
            itemProductBinding.ratingIcon.drawable.setTint(lightGreen)
        }
        else{
            itemProductBinding.ratingText.setTextColor(green)
            itemProductBinding.ratingIcon.drawable.setTint(green)
        }
    }
}