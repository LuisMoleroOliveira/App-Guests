package br.com.molero.guests.view.viewholder

import android.app.AlertDialog
import android.view.OnReceiveContentListener
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuAdapter
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import br.com.molero.guests.R
import br.com.molero.guests.databinding.RowGuestBinding
import br.com.molero.guests.service.model.GuestModel
import br.com.molero.guests.view.adapter.GuestAdapter
import br.com.molero.guests.view.listener.GuestListener

class GuestViewHolder(itemView: RowGuestBinding, private val listener: GuestListener ) : RecyclerView.ViewHolder(itemView.root) { //binding

//class GuestViewHolder(itemView:View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {// findViewById
    private lateinit var binding: RowGuestBinding //binding

    fun bind(guest:GuestModel){

        val textName = itemView.findViewById<TextView>(R.id.text_name)// --- findViewById
        val textDelete = itemView.findViewById<ImageView>(R.id.image_delete)
        val textEdit = itemView.findViewById<ImageView>(R.id.image_edit)
        //val textName = itemView.findViewById<TextView>(binding.textName.id)

        textName.text = guest.name

        textEdit.setOnClickListener{
            listener.onClick(guest.id)
        }
        textDelete.setOnClickListener{

            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover){dialog,which ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()

            true
        }
    }
}