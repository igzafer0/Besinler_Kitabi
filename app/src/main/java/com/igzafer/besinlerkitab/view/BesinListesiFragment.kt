package com.igzafer.besinlerkitab.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.besinlerkitab.R
import com.igzafer.besinlerkitab.adapter.besinAdapter
import com.igzafer.besinlerkitab.model.besinState
import com.igzafer.besinlerkitab.viewmodel.besinListesiViewModel
import kotlinx.android.synthetic.main.fragment_besin_listesi.*
import java.util.List.of

class BesinListesiFragment : Fragment() {

    private lateinit var viewModel: besinListesiViewModel
    private val adapter = besinAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(besinListesiViewModel::class.java)
        viewModel.refreshData()
        listRecy.layoutManager = LinearLayoutManager(context)
        listRecy.adapter = adapter

        listSwipe.setOnRefreshListener {
            viewModel.refreshFromInternet()
            listSwipe.isRefreshing=false
        }
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.besinler.observe(viewLifecycleOwner, Observer { besinler ->
            besinler?.let {
                listRecy.visibility = View.VISIBLE
                adapter.besinListesiniGuncelle(besinler)
                listProgress.visibility = View.GONE
                listRecy.visibility = View.VISIBLE
                lisHataMesaji.visibility = View.GONE
            }
        })
        viewModel.besinDurum.observe( viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if (it==besinState.YUKLENIYOR) {
                    listRecy.visibility = View.GONE
                    lisHataMesaji.visibility = View.GONE
                    listProgress.visibility = View.VISIBLE
                } else if(it==besinState.HATA){
                    listProgress.visibility = View.GONE
                    listRecy.visibility = View.GONE
                    lisHataMesaji.visibility = View.VISIBLE
                }
            }
        })
    }
}