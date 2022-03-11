package com.visarut.myapplication.view.feature.featureBottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.visarut.myapplication.R
import com.visarut.myapplication.databinding.BottomSheetFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel: BottomSheetViewModel by viewModel()
    private lateinit var binding: BottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        viewModel.fetchCoinDetail(arguments?.getString("uuid") ?: "")
        viewModel.coinList.observe(viewLifecycleOwner, {
            binding.fullName = it.name
            binding.symbol = it.symbol
            binding.price = it.price
            binding.marketCap = it.marketCap
            binding.imageUrl = it.iconUrl
            binding.description = it.description
        })

        return binding.root
    }

}