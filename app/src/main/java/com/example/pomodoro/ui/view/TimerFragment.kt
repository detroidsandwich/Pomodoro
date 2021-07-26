package com.example.pomodoro.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pomodoro.databinding.FragmentTimerBinding
import com.example.pomodoro.ui.common.addNavigationBarInsets
import com.example.pomodoro.ui.common.addStatusBarInsets
import com.example.pomodoro.ui.common.viewLifecycleLaunch
import com.example.pomodoro.ui.view.adapter.RecyclerViewTimerAdapter
import com.example.pomodoro.ui.view.adapter.TimerViewHolderListener
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TimerViewModel by viewModel()

    private val timerAdapter = RecyclerViewTimerAdapter(
        listener = object : TimerViewHolderListener {
            override fun onStart(id: Long) {
                viewModel.startTimer(id)
            }

            override fun onStop(id: Long) {
                viewModel.stopTimer(id)
            }

            override fun onDelete(id: Long) {
                viewModel.deleteTimer(id)
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            appBarLayout.addStatusBarInsets()
            root.addNavigationBarInsets()

            rvMain.apply {
                adapter = timerAdapter
                layoutManager = LinearLayoutManager(context)
            }

            buttonAddTimer.setOnClickListener {
                val time = editText.text.toString().toLong()
                viewModel.addTimer(time * 60)
            }

            editText.addTextChangedListener {
                buttonAddTimer.isEnabled = !it.isNullOrBlank()
            }
        }

        viewLifecycleLaunch {
            viewModel.timersFlow.collect {
                timerAdapter.submitList(it)
            }
        }

        viewLifecycleLaunch {
            viewModel.endTimerFlow.collect {
                timerAdapter.submitList(timerAdapter.currentList)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}