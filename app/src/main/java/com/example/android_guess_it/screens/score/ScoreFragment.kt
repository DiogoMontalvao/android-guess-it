package com.example.android_guess_it.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android_guess_it.R
import com.example.android_guess_it.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment() {
    private lateinit var binding: ScoreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false)

        val args by navArgs<ScoreFragmentArgs>()

        binding.scoreText.text = args.score.toString()

        binding.playAgainButton.setOnClickListener {
            findNavController().navigate(
                ScoreFragmentDirections.actionScoretoGame()
            )
        }

        return binding.root
    }
}