package com.example.android_guess_it.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android_guess_it.R
import com.example.android_guess_it.databinding.GameFragmentBinding

class GameFragment : Fragment() {
    private var word = ""
    private var score = 0

    private var wordList = mutableListOf(
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

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

        resetList()
        nextWord()

        binding.gotItButton.setOnClickListener { onGotIt() }
        binding.skipButton.setOnClickListener { onSkip() }

        updateScoreText()
        updateWordText()

        return binding.root
    }

    private fun resetList() {
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            findNavController().navigate(GameFragmentDirections.actionGametoScore(score))
            return
        }

        word = wordList.removeAt(0)

        updateWordText()
        updateScoreText()
    }

    private fun updateWordText() {
        binding.wordText.text = word
    }

    private fun updateScoreText() {
        binding.scoreText.text = getString(R.string.score, score)
    }

    private fun onGotIt() {
        score++
        nextWord()
    }

    private fun onSkip() {
        score--
        nextWord()
    }
}