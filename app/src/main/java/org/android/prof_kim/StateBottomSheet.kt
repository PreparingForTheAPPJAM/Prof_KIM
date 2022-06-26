package org.android.prof_kim

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.android.prof_kim.ReadViewModel.Companion.STATE_COMPLETED
import org.android.prof_kim.ReadViewModel.Companion.STATE_RESERVING
import org.android.prof_kim.ReadViewModel.Companion.STATE_SELLING
import org.android.prof_kim.databinding.BottomSheetStateBinding


class StateBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetStateBinding? = null
    val binding get() = _binding ?: error(getString(R.string.binding_error))
    private val readViewModel by activityViewModels<ReadViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = BottomSheetStateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet =
            dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        initSellingClickListener()
        initCompletedClickListener()
        initReservingClickListener()
    }

    private fun initSellingClickListener() {
        binding.tvStateSelling.setOnClickListener {
            // 서버 통신
            readViewModel.initState(STATE_SELLING)
            dismiss()
        }
    }

    private fun initCompletedClickListener() {
        binding.tvStateCompleted.setOnClickListener {
            // 서버 통신
            readViewModel.initState(STATE_COMPLETED)
            dismiss()
        }
    }

    private fun initReservingClickListener() {
        binding.tvStateReserving.setOnClickListener {
            // 서버 통신
            readViewModel.initState(STATE_RESERVING)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}