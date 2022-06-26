package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.recycler.products

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ItemProductBinding

class ProductViewHolder(
    private val itemProductBinding: ItemProductBinding,
    onProductClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemProductBinding.root) {

    companion object{
        private const val red = "#CA0000"
        private const val orange = "#F17100"
        private const val yellow = "#FFC61A"
        private const val lightGreen = "#31C657"
        private const val green = "#08B123"
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
            itemProductBinding.ratingText.setTextColor(Color.parseColor(red))
            itemProductBinding.ratingIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_rating_red))
        }
        else if(rating > 4.2 && rating <= 4.4){
            itemProductBinding.ratingText.setTextColor(Color.parseColor(orange))
            itemProductBinding.ratingIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_rating_orange))
        }
        else if(rating > 4.4 && rating <= 4.6){
            itemProductBinding.ratingText.setTextColor(Color.parseColor(yellow))
            itemProductBinding.ratingIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_rating_yellow))
        }
        else if(rating > 4.6 && rating <= 4.8){
            itemProductBinding.ratingText.setTextColor(Color.parseColor(lightGreen))
            itemProductBinding.ratingIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_rating_light_green))
        }
        else{
            itemProductBinding.ratingText.setTextColor(Color.parseColor(green))
            itemProductBinding.ratingIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_rating_green))
        }
    }
}