package rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProductEntity

@Dao
abstract class CartDao {

    @Query("SELECT * FROM cart WHERE username == :username")
    abstract fun getAllByUser(username: String): Observable<List<CartProductEntity>>

    @Query("SELECT * FROM cart WHERE username == :username AND id == :productId")
    abstract fun getProductByUser(username: String, productId: Int): CartProductEntity

    @Query("DELETE FROM cart WHERE username == :username")
    abstract fun removeAllByUser(username: String): Completable

    @Insert
    abstract fun addCartProduct(cartProduct: CartProductEntity)

    @Update
    abstract fun updateCartProduct(cartProduct: CartProductEntity)

    open fun addToCart(cartProduct: CartProductEntity): Completable{
        return Completable.fromCallable {
            try {
                addCartProduct(cartProduct)
            }
            catch (e: SQLiteConstraintException){
                val cartProductForUpdate = getProductByUser(cartProduct.username, cartProduct.id)
                cartProductForUpdate.amount = cartProductForUpdate.amount + 1
                updateCartProduct(cartProductForUpdate)
            }
        }
    }

    @Query("DELETE FROM cart WHERE username == :username AND id == :productId")
    abstract fun removeFromCartByUser(username: String, productId: Int): Completable
}