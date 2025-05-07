package com.example.android_guess_it.screens.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val wordList = mutableListOf(
        "queen",
        "hospital",
        "basketball",
        "cat",
        "change",
        "snail",
        "soup",
        "calendar",
        "sad",
        "desk",
        "guitar",
        "home",
        "railway",
        "zebra",
        "jelly",
        "car",
        "crow",
        "trade",
        "bag",
        "roll",
        "bubble"
    )

    val word = MutableLiveData<String>()
    val score = MutableLiveData<Int>()

    init {
        word.value = ""
        score.value = 0

        resetList()
        nextWord()
    }

    private fun resetList() {
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) return
        // TODO onGameFinished()

        word.value = wordList.removeAt(0)
    }

    fun onSkip() {
        score.value = score.value?.minus(1)
        nextWord()
    }

    fun onGotIt() {
        score.value = score.value?.plus(1)
        nextWord()
    }
}