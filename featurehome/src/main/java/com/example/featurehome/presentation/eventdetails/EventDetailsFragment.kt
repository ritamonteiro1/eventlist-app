package com.example.featurehome.presentation.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.core.R
import com.example.core.model.NetworkErrorException
import com.example.core.model.ServerErrorException
import com.example.core.utils.createLoadingDialog
import com.example.core.utils.downloadImage
import com.example.core.utils.toStringDate
import com.example.featurehome.databinding.FragmentEventDetailsBinding
import getErrorMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsFragment : Fragment() {
    private val viewModel: EventDetailsViewModel by viewModel()
    private val navArgs: EventDetailsFragmentArgs by navArgs()
    private var _binding: FragmentEventDetailsBinding? = null
    private val loadingDialog by lazy { activity?.createLoadingDialog() }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEventDetails(navArgs.eventId)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.stateEventDetails.observe(viewLifecycleOwner) { state ->
            handleState(state)
        }
    }

    private fun handleState(state: EventDetailsState) {
        when (state) {
            is ErrorEventDetails -> {
                val message = getString(state.error.getErrorMessage())
                loadingDialog?.dismiss()
                binding.imageView.visibility = View.GONE
                binding.titleTextView.visibility = View.GONE
                binding.descriptionTextView.visibility = View.GONE
                binding.dateTextView.visibility = View.GONE
                binding.priceTextView.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.errorButton.visibility = View.VISIBLE
                binding.doCheckinButton.visibility = View.GONE
                binding.errorText.text = message
                setOnClickButton()
            }
            is LoadingEventDetails -> {
                loadingDialog?.show()
                binding.errorText.visibility = View.GONE
                binding.errorButton.visibility = View.GONE
                binding.imageView.visibility = View.GONE
                binding.titleTextView.visibility = View.GONE
                binding.descriptionTextView.visibility = View.GONE
                binding.dateTextView.visibility = View.GONE
                binding.priceTextView.visibility = View.GONE
                binding.doCheckinButton.visibility = View.GONE
            }
            is SuccessEventDetails -> {
                loadingDialog?.dismiss()
                binding.imageView.downloadImage(state.eventDetails.image)
                binding.titleTextView.text = state.eventDetails.title
                binding.descriptionTextView.text = state.eventDetails.description
                binding.dateTextView.text = state.eventDetails.date.toStringDate()
                binding.priceTextView.text = state.eventDetails.price.toString()
                binding.errorText.visibility = View.GONE
                binding.errorButton.visibility = View.GONE
                binding.imageView.visibility = View.VISIBLE
                binding.titleTextView.visibility = View.VISIBLE
                binding.descriptionTextView.visibility = View.VISIBLE
                binding.dateTextView.visibility = View.VISIBLE
                binding.priceTextView.visibility = View.VISIBLE
                binding.doCheckinButton.visibility = View.VISIBLE
            }
        }
    }

    private fun setOnClickButton() {
        binding.errorButton.setOnClickListener {
            viewModel.getEventDetails(navArgs.eventId)
        }
    }
}