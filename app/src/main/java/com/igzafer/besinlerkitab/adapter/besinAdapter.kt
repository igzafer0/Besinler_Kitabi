package com.igzafer.besinlerkitab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.besinlerkitab.R
import com.igzafer.besinlerkitab.model.besinModel
import com.igzafer.besinlerkitab.utils.gorselIndir
import com.igzafer.besinlerkitab.utils.placeholderYap
import com.igzafer.besinlerkitab.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_row.view.*

class besinAdapter(val besinListesi: ArrayList<besinModel>) :
    RecyclerView.Adapter<besinAdapter.BesinViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.besin_row, parent, false)
        return BesinViewHolder(view)
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.itemView.besinIsim.text=besinListesi.get(position).besinIsim
        holder.itemView.besinKalori.text=besinListesi.get(position).besinKalori

        holder.itemView.besinFoto.gorselIndir(besinListesi.get(position).besinGorsel, placeholderYap(holder.itemView.context))
        holder.itemView.setOnClickListener{
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(position)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun besinListesiniGuncelle(yeniBesinListesi:List<besinModel>){
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }
    class BesinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
