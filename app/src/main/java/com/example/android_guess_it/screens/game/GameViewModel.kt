package com.examplue.android_guess_it.screens.game

import androidx.lifecycle.LiveData
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

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinished

    init {
        _word.value = ""
        _score.value = 0
        _eventGameFinished.value = false

        resetList()
        nextWord()
    }

    private fun resetList() {
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            _eventGameFinished.value = true
            return
        }

        _word.value = wordList.removeAt(0)
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onGotIt() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }

    fun onGameFinishedComplete() {
        _eventGameFinished.value = false
    }
}