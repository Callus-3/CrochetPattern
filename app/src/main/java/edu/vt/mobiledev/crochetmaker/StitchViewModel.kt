package edu.vt.mobiledev.crochetmaker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StitchViewModel : ViewModel(){
    private val stitchRepository = StitchRepository.get()
    private val _stitches : MutableStateFlow<List<Stitch>> = MutableStateFlow(emptyList())
    val stitches: StateFlow<List<Stitch>>
        get() = _stitches.asStateFlow()


    init {
        viewModelScope.launch {
            stitchRepository.getStitches().collect{
                _stitches.value = it
            }
        }
    }



}