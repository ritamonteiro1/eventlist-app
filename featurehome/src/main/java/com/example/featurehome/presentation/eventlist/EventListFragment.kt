package com.example.featurehome.presentation.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.utils.createLoadingDialog
import com.example.core.utils.setupToolbar
import com.example.featurehome.databinding.FragmentEventListBinding
import com.example.featurehome.domain.model.Event
import getErrorMessage
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
        setupToolbar(binding.toolBar)
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
                handleErrorStateEventList(state)
            }
            is Loading -> {
                handleLoadingStateEventList()
            }
            is Success -> {
                handleSuccessStateEventList(state)
            }
        }
    }

    private fun handleSuccessStateEventList(state: Success) {
        loadingDialog?.dismiss()
        val eventListAdapter = setupEventListAdapter(state.eventList)
        setupRecyclerView(eventListAdapter)
        binding.eventListRecyclerView.visibility = View.VISIBLE
        binding.errorText.visibility = View.GONE
        binding.errorButton.visibility = View.GONE
    }

    private fun handleLoadingStateEventList() {
        loadingDialog?.show()
        binding.eventListRecyclerView.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.errorButton.visibility = View.GONE
    }

    private fun handleErrorStateEventList(state: Error) {
        val message = getString(state.error.getErrorMessage())
        loadingDialog?.dismiss()
        binding.eventListRecyclerView.visibility = View.GONE
        binding.errorText.visibility = View.VISIBLE
        binding.errorButton.visibility = View.VISIBLE
        binding.errorText.text = message
        setOnClickButton()
    }

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