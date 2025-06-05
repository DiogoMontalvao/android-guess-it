package com.example.android_guess_it.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import timber.log.Timber

class GameViewModel : ViewModel() {
    companion object {
        private const val PANIC_VIBRATION = 10000L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 15000L
    }

    private lateinit var wordList: MutableList<String>
    private var timer: CountDownTimer

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _currentTimeLong = MutableLiveData<Long>()
    val currentTimeLong: LiveData<Long>
        get() = _currentTimeLong

    val currentTime = currentTimeLong.map { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventVibration = MutableLiveData<VibrationType>()
    val eventVibration: LiveData<VibrationType>
        get() = _eventVibration

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinished

    init {
        _word.value = ""
        _currentTimeLong.value = 0
        _score.value = 0
        _eventVibration.value = VibrationType.NO_VIBRATION
        _eventGameFinished.value = false

        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTimeLong.value = millisUntilFinished / ONE_SECOND

                if (millisUntilFinished <= PANIC_VIBRATION) {
                    _eventVibration.value = VibrationType.COUNTDOWN_PANIC
                }
            }

            override fun onFinish() {
                _eventVibration.value = VibrationType.GAME_OVER
                _eventGameFinished.value = true
            }
        }.start()
    }

    private fun resetList() {
        wordList = mutableListOf(
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

        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty())
            resetList()

        _word.value = wordList.removeAt(0)
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onGotIt() {
        _eventVibration.value = VibrationType.CORRECT

        _score.value = _score.value?.plus(1)
        nextWord()
    }

    fun eventGameFinishedComplete() {
        _eventGameFinished.value = false
    }

    fun eventVibrationComplete() {
        _eventVibration.value = VibrationType.NO_VIBRATION
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}