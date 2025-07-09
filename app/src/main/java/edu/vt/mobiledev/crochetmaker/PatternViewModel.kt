package edu.vt.mobiledev.crochetmaker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PatternViewModel : ViewModel() {

    private val patternRepository = PatternRepository.get()
    private val _patterns: MutableStateFlow<List<Pattern>> = MutableStateFlow(emptyList())
    val patterns: StateFlow<List<Pattern>>
        get() = _patterns.asStateFlow()

    init {
        viewModelScope.launch {
            patternRepository.getPatterns().collect{
                _patterns.value = it
            }
        }
    }

    suspend fun addPattern(pattern: Pattern){
        patternRepository.addPattern(pattern)
    }
}