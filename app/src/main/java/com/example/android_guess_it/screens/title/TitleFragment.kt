package com.example.android_guess_it.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android_guess_it.R
import com.example.android_guess_it.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {
    private lateinit var binding: TitleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.title_fragment, container, false)

        binding.playButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitletoGame())
        }

        return binding.root
    }
}