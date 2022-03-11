package com.visarut.myapplication.view.feature.featureHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.visarut.myapplication.R
import com.visarut.myapplication.databinding.HomeFragmentBinding
import com.visarut.myapplication.domain.model.CoinsData
import com.visarut.myapplication.view.feature.featureBottomSheet.BottomSheetFragment
import com.visarut.myapplication.view.feature.featureHome.controller.HomeFragmentController
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HomeFragmentController.AddOnItemSelected {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: HomeFragmentBinding
    private val bottomSheetFragment = BottomSheetFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        binding.lifecycleOwner = this

        val homeFragmentController = HomeFragmentController()

        homeFragmentController.setItemSelectListener(this)

        binding.epoxyList.setController(homeFragmentController)

        viewModel.coinList.observe(viewLifecycleOwner, {
            val coinsData = CoinsData(viewModel.coinList)
            homeFragmentController.setData(coinsData)
        })

        return binding.root
    }

    override fun onClickCoin(uuid: String) {
        val bundle = Bundle()
        bundle.putString("uuid", uuid)

        bottomSheetFragment.arguments = bundle
        bottomSheetFragment.show(childFragmentManager, "BottomSheetDialog")
    }

}