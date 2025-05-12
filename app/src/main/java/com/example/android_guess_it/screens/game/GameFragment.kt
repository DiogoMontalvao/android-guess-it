package com.example.android_guess_it.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_guess_it.R
import com.example.android_guess_it.databinding.GameFragmentBinding

class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        binding.gotItButton.setOnClickListener {
            viewModel.onGotIt()
        }

        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = getString(R.string.score, newScore)
        })

        viewModel.eventGameFinished.observe(viewLifecycleOwner, Observer { gameHasFinished ->
            if (gameHasFinished) {
                finishGame()
                viewModel.eventGameFinishedComplete()
            }
        })

        viewModel.currentTime.observe(viewLifecycleOwner, Observer { timer ->
            binding.timerText.text = viewModel.currentTime.value
        })

        return binding.root
    }

    fun finishGame() {
        val currentScore = viewModel.score.value ?: 0

        findNavController().navigate(
            GameFragmentDirections.actionGametoScore(currentScore)
        )
    }
}