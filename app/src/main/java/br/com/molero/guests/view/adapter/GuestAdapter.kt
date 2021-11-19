package br.com.molero.guests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.molero.guests.databinding.RowGuestBinding
import br.com.molero.guests.service.model.GuestModel
import br.com.molero.guests.view.listener.GuestListener
import br.com.molero.guests.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        //val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest,parent,false) //findById
        val inflater = LayoutInflater.from(parent.context)
        val item = RowGuestBinding.inflate(inflater, parent, false)
        return GuestViewHolder(item,mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        mGuestList = list
        notifyDataSetChanged()
    }
    fun attachListener(listener : GuestListener){
        mListener = listener
    }

}