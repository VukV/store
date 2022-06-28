package rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProductEntity
import timber.log.Timber

@Dao
abstract class CartDao {

    @Query("SELECT * FROM cart WHERE username == :username")
    abstract fun getAllByUser(username: String): Observable<List<CartProductEntity>>

    @Query("DELETE FROM cart WHERE username == :username")
    abstract fun removeAllByUser(username: String): Completable

    @Insert
    abstract fun addCartProduct(cartProduct: CartProductEntity): Completable

    @Update
    abstract fun updateCartProduct(cartProduct: CartProductEntity): Completable

    fun addToCart(cartProduct: CartProductEntity){
        try {
            Timber.e("USAO U TRY")
            addCartProduct(cartProduct).blockingAwait()
        }
        catch (e: SQLiteConstraintException){
            Timber.e("USAO U CATCH")
            cartProduct.amount = cartProduct.amount + 1
            updateCartProduct(cartProduct).blockingAwait()
        }
    }

    @Query("DELETE FROM cart WHERE username == :username AND id == :productId")
    abstract fun removeFromCartByUser(username: String, productId: Int): Completable
}