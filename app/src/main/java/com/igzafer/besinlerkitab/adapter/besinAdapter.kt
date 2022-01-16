package com.igzafer.besinlerkitab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.besinlerkitab.R
import com.igzafer.besinlerkitab.databinding.BesinRowBinding
import com.igzafer.besinlerkitab.model.BesinModel
import com.igzafer.besinlerkitab.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_row.view.*

class besinAdapter(val besinListesi: ArrayList<BesinModel>) :
    RecyclerView.Adapter<besinAdapter.BesinViewHolder>(), IBesinClickListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<BesinRowBinding>(inflater, R.layout.besin_row, parent, false)
        return BesinViewHolder(view)
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.view.besin = besinListesi[position]
        holder.view.listener=this
        /*
        holder.itemView.besinIsim.text = besinListesi.get(position).besinIsim
        holder.itemView.besinKalori.text = besinListesi.get(position).besinKalori

        holder.itemView.besinFoto.gorselIndir(
            besinListesi.get(position).besinGorsel,
            placeholderYap(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            val action =
                BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(
                    besinListesi.get(position).uuid
                )
            Navigation.findNavController(it).navigate(action)
        }
        */

    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun besinListesiniGuncelle(yeniBesinListesi: List<BesinModel>) {
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }

    class BesinViewHolder(var view: BesinRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun besinTiklandi(view: View) {
        val action =
            BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(
                view.besinUuid.text.toString().toInt()
            )
        Navigation.findNavController(view).navigate(action)
    }
}
