package com.example.android_guess_it.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android_guess_it.R
import com.example.android_guess_it.databinding.ScoreFragmentBinding
import timber.log.*

class ScoreFragment : Fragment() {
    private lateinit var binding: ScoreFragmentBinding
    private lateinit var viewModelFactory: ScoreViewModelFactory
    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = navArgs<ScoreFragmentArgs>()

        binding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false)

        viewModelFactory = ScoreViewModelFactory(args.value.score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        viewModel.score.observe(viewLifecycleOwner, Observer { gameScore ->
            binding.scoreText.text = gameScore.toString()
        })

        binding.playAgainButton.setOnClickListener {
            findNavController().navigate(
                ScoreFragmentDirections.actionScoretoGame()
            )
        }

        return binding.root
    }
}