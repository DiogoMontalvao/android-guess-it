package com.example.android_guess_it.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
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

        return binding.root
    }
}