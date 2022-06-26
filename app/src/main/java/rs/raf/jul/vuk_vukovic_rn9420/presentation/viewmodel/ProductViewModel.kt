package rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductResource
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.product.ProductRepository
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.ProductContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.ProductState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel(), ProductContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()
    override val productState: MutableLiveData<ProductState> = MutableLiveData()

    init {
        val subscription = publishSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                productRepository
                    .fetchAllBySearch(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    //TODO
                    // kako postaviti ProductState ako imam Unit?
                    // jer ako mi Repository direktno vrati List<Product>, onda ne radim preko Resource-a
                },
                {

                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAll() {
        val subscription = productRepository
            .fetchAll()
            .startWith(ProductResource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it){
                        is ProductResource.Loading -> productState.value = ProductState.Loading
                        is ProductResource.Success -> productState.value = ProductState.DataFetched
                        is ProductResource.Error -> productState.value = ProductState.Error("Server error")
                    }
                },
                {
                    productState.value = ProductState.Error("Server error")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAll() {
        val subscription = productRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    productState.value = ProductState.Success(it)
                },
                {
                    productState.value = ProductState.Error("Data error")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllBySearch(searchTag: String) {
        publishSubject.onNext(searchTag)
    }

    override fun getAllByCategory(category: String) {
        val subscription = productRepository
            .fetchAllByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        subscriptions.add(subscription)
    }
}