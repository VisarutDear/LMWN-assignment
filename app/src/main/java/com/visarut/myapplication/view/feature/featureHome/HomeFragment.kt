package com.visarut.myapplication.view.feature.featureHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var loadMoreScollListener: RecyclerView.OnScrollListener

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchCoinList()
        }
        initEndlessScroll()
        val homeFragmentController = HomeFragmentController()

        homeFragmentController.setItemSelectListener(this)

        binding.epoxyList.setController(homeFragmentController)
        binding.epoxyList.addOnScrollListener(loadMoreScollListener)

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

    private fun fetchCoinList() {
        viewModel.fetchCoin()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun initEndlessScroll() {
        loadMoreScollListener= object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)) {
                    if (viewModel.isLoadMore()){
                        viewModel.fetchMoreCoin()
                    }
                }
            }
        }
    }
}