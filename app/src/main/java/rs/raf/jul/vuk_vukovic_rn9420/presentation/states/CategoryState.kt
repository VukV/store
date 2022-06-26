package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

sealed class CategoryState {
    object Loading: CategoryState()
    object DataFetched: CategoryState()
    data class Success(val categories: List<String>): CategoryState()
    data class Error(val message: String): CategoryState()
}