package com.example.featurehome.presentation.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.R
import com.example.core.model.NetworkErrorException
import com.example.core.model.ServerErrorException
import com.example.core.utils.createLoadingDialog
import com.example.featurehome.databinding.FragmentEventListBinding
import com.example.featurehome.domain.model.Event
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment : Fragment() {
    private val viewModel: EventListViewModel by viewModel()
    private var _binding: FragmentEventListBinding? = null
    private val loadingDialog by lazy { activity?.createLoadingDialog() }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEventList()
        setupObservers()
    }

    private fun setOnClickButton() {
        binding.errorButton.setOnClickListener {
            viewModel.getEventList()
        }
    }

    private fun setupObservers() {
        viewModel.stateEventList.observe(viewLifecycleOwner) { state ->
            handleState(state)
        }
    }

    private fun handleState(state: EventListState) {
        when (state) {
            is Error -> {
                val message = getErrorMessage(state.error)
                loadingDialog?.dismiss()
                binding.eventListRecyclerView.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.errorButton.visibility = View.VISIBLE
                binding.errorText.text = message
                setOnClickButton()
            }
            is Loading -> {
                loadingDialog?.show()
                binding.eventListRecyclerView.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.errorButton.visibility = View.GONE
            }
            is Success -> {
                loadingDialog?.dismiss()
                val eventListAdapter = setupEventListAdapter(state.eventList)
                setupRecyclerView(eventListAdapter)
                binding.eventListRecyclerView.visibility = View.VISIBLE
                binding.errorText.visibility = View.GONE
                binding.errorButton.visibility = View.GONE
            }
        }
    }

    private fun getErrorMessage(error: Throwable) =
        if (error is NetworkErrorException || error is ServerErrorException)
            getString(R.string.network_error_exception) else getString(R.string.generic_error_exception)

    private fun setupEventListAdapter(eventList: List<Event>): EventListAdapter {
        return EventListAdapter(eventList) { event ->
            val action =
                EventListFragmentDirections.actionEnterpriseListToEnterpriseDetails(event.id)
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView(eventListAdapter: EventListAdapter) {
        binding.eventListRecyclerView.adapter = eventListAdapter
        val layoutManager = LinearLayoutManager(
            activity, LinearLayoutManager.VERTICAL, false
        )
        binding.eventListRecyclerView.layoutManager = layoutManager
    }
}