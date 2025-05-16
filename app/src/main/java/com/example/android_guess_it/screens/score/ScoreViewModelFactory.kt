package com.example.android_guess_it.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(viewModelClass: Class<T>): T {
        if (viewModelClass.isAssignableFrom(ScoreViewModel::class.java))
            return ScoreViewModel(finalScore) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}