package com.example.featurehome.presentation.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.featurehome.databinding.FragmentEventListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment : Fragment() {
    private val viewModel: EventListViewModel by viewModel()
    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}