package com.example.featurehome.presentation.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.featurehome.databinding.FragmentEventDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsFragment : Fragment() {
    private val viewModel: EventDetailsViewModel by viewModel()

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}