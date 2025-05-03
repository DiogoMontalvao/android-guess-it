package com.example.android_guess_it.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_guess_it.R
import com.example.android_guess_it.databinding.GameFragmentBinding
import timber.log.Timber

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

        binding.gotItButton.setOnClickListener {
            viewModel.onGotIt()
            updateScoreText()
            updateWordText()
        }

        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
            updateScoreText()
            updateWordText()
        }

        updateScoreText()
        updateWordText()

        return binding.root
    }

    private fun updateWordText() {
        binding.wordText.text = viewModel.word
    }

    private fun updateScoreText() {
        binding.scoreText.text = getString(R.string.score, viewModel.score)
    }
}