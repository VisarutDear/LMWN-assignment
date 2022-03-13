package com.visarut.myapplication.view.feature.featureBottomSheet

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.visarut.myapplication.databinding.BottomSheetFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class BottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel: BottomSheetViewModel by viewModel()
    private lateinit var binding: BottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        lifecycleScope.launch {
            viewModel.fetchCoinDetail(arguments?.getString("uuid") ?: "")
        }
        viewModel.coinDetail.observe(viewLifecycleOwner, { it ->
            binding.fullName = it.name
            binding.symbol = "(" + it.symbol +")"
            binding.price = "$ ${String.format("%.2f", it.price.toFloat())}"
            binding.marketCap = "$ ${it.marketCap}"
            binding.imageUrl = it.iconUrl
            binding.textViewCoinDescription.text =
                it.description?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT) } ?: "No description"
            binding.website = it.websiteUrl
            binding.buttonCoinWebsite.setOnClickListener { _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.websiteUrl))
                context?.startActivity(intent)
            }
            binding.textViewCoinFullName.setTextColor(Color.parseColor(it.color ?: "#333333"))
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        lifecycleScope.launch {
//            viewModel.fetchCoinDetail(arguments?.getString("uuid") ?: "")
//        }
    }

}