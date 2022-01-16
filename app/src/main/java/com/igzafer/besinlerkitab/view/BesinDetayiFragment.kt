package com.igzafer.besinlerkitab.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.igzafer.besinlerkitab.R
import com.igzafer.besinlerkitab.databinding.FragmentBesinDetayiBinding
import com.igzafer.besinlerkitab.utils.gorselIndir
import com.igzafer.besinlerkitab.utils.placeholderYap
import com.igzafer.besinlerkitab.viewmodel.besinDetayiViewModel
import kotlinx.android.synthetic.main.besin_row.*
import kotlinx.android.synthetic.main.fragment_besin_detayi.*
import kotlinx.android.synthetic.main.fragment_besin_detayi.besinIsim
import kotlinx.android.synthetic.main.fragment_besin_detayi.besinKalori

class BesinDetayiFragment : Fragment() {
    private lateinit var viewModel: besinDetayiViewModel
    private var besinId = 0
    private lateinit var dataBinding: FragmentBesinDetayiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_besin_detayi, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            besinId = BesinDetayiFragmentArgs.fromBundle(it).besinId

        }
        viewModel = ViewModelProviders.of(this).get(besinDetayiViewModel::class.java)
        viewModel.roomVerisiniAl(besinId)
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer { besin ->
            besin?.let {
                dataBinding.besin = besin
            }
        })
    }
}