package com.example.featurehome.presentation.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.core.utils.createLoadingDialog
import com.example.core.utils.downloadImage
import com.example.core.utils.setupToolbar
import com.example.core.utils.showToast
import com.example.core.utils.toStringDate
import com.example.featurehome.R
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
        setupToolbar(binding.toolBar)
    }

    private fun setupObservers() {
        viewModel.stateEventDetails.observe(viewLifecycleOwner) { state ->
            handleEventDetailsState(state)
        }
        viewModel.stateDoCheckIn.observe(viewLifecycleOwner) { state ->
            handleDoCheckInState(state)
        }
    }

    private fun handleDoCheckInState(state: DoCheckInState) {
        when (state) {
            is ErrorDoCheckIn -> {
                showToast(getString(R.string.fragment_event_details_error_checkin))
            }
            is SuccessDoCheckIn -> {
                showToast(getString(R.string.fragment_event_details_success_checkin))
            }
        }
    }

    private fun handleEventDetailsState(state: EventDetailsState) {
        when (state) {
            is ErrorEventDetails -> {
                handleErrorStateEventDetails(state)
            }
            is LoadingEventDetails -> {
                handleLoadingStateEventDetails()
            }
            is SuccessEventDetails -> {
                handleSuccessStateEventDetails(state)
            }
        }
    }

    private fun handleSuccessStateEventDetails(state: SuccessEventDetails) {
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
        setOnClickDoCheckInButton()
    }

    private fun handleLoadingStateEventDetails() {
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

    private fun handleErrorStateEventDetails(state: ErrorEventDetails) {
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
        setOnClickErrorButton()
    }

    private fun setOnClickDoCheckInButton() {
        binding.doCheckinButton.setOnClickListener {
            viewModel.doCheckIn(navArgs.eventId)
        }
    }

    private fun setOnClickErrorButton() {
        binding.errorButton.setOnClickListener {
            viewModel.getEventDetails(navArgs.eventId)
        }
    }
}