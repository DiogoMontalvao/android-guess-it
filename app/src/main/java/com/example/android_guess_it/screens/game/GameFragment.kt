package com.example.android_guess_it.screens.game

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
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

        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.gameViewModel = viewModel

        viewModel.eventVibration.observe(viewLifecycleOwner, Observer { vibrationType ->
            if (vibrationType != VibrationType.NO_VIBRATION) {
                vibrate(vibrationType)
                viewModel.eventVibrationComplete()
            }
        })

        viewModel.eventGameFinished.observe(viewLifecycleOwner, Observer { gameHasFinished ->
            if (gameHasFinished) {
                finishGame()
                viewModel.eventGameFinishedComplete()
            }
        })

        return binding.root
    }

    fun finishGame() {
        val currentScore = viewModel.score.value ?: 0

        findNavController().navigate(
            GameFragmentDirections.actionGametoScore(currentScore)
        )
    }

    private fun vibrate(pattern: VibrationType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorManager.defaultVibrator

            vibrator.vibrate(VibrationEffect.createWaveform(pattern.pattern, -1))
        } else {
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(VibrationEffect.createWaveform(pattern.pattern, -1))
        }
    }
}