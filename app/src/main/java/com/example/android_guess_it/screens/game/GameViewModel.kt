package com.example.android_guess_it.screens.game

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

    var word = ""
    var score = 0

    init {
        resetList()
        nextWord()
    }

    private fun resetList() {
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) return

        word = wordList.removeAt(0)
    }

    fun onGotIt() {
        score++
        nextWord()
    }

    fun onSkip() {
        score--
        nextWord()
    }
}