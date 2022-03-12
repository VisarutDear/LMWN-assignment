package com.visarut.myapplication.view.feature.featureBottomSheet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
        binding.viewModel = viewModel
        viewModel.fetchCoinDetail(arguments?.getString("uuid") ?: "")
        viewModel.coinDetail.observe(viewLifecycleOwner, {
            binding.fullName = it.name
            binding.symbol = "(" + it.symbol +")"
            binding.price = "$ "+ it.price
            binding.marketCap = "$ "+it.marketCap
            binding.imageUrl = it.iconUrl
            binding.description = it.description ?: "No description"
            binding.website = it.websiteUrl
            binding.buttonCoinWebsite.setOnClickListener { _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.websiteUrl))
                context?.startActivity(intent)
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}