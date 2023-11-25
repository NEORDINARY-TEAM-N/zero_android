package com.hackathon.zero.presentation.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hackathon.zero.R
import com.hackathon.zero.base.BaseFragment
import com.hackathon.zero.data.Product
import com.hackathon.zero.databinding.FragmentMyBinding

class MyFragment : BaseFragment<FragmentMyBinding>(R.layout.fragment_my) {

    private val adapter by lazy {
        productRVAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RVAdapter.adapter = adapter
        val list = listOf<Product>(
            Product("제로콜라",0.1,1,250,1,),
            Product("dd",0.2,2,1,1,),
            Product("dd",0.1,1,2,1,)
        )
        adapter.setList(list)
    }
}