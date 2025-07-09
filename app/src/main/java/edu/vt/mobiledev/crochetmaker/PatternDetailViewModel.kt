package edu.vt.mobiledev.crochetmaker

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class PatternDetailViewModelFactory
    (private val patternId: UUID) : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        return PatternDetailViewModel(patternId) as T
    }
}

class  PatternDetailViewModel(patternId: UUID): ViewModel() {
    private val patternRepository = PatternRepository.get()
    private val _pattern: MutableStateFlow<Pattern?> = MutableStateFlow(null)
    val pattern: StateFlow<Pattern?> = _pattern.asStateFlow()

    init {
        viewModelScope.launch {
            _pattern.value = patternRepository.getPattern(patternId)
        }
    }


}