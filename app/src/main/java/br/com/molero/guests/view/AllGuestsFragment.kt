package br.com.molero.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.molero.guests.R
import br.com.molero.guests.databinding.FragmentAllBinding
import br.com.molero.guests.service.constants.GuestConstants
import br.com.molero.guests.view.adapter.GuestAdapter
import br.com.molero.guests.view.listener.GuestListener
import br.com.molero.guests.viwmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var mViewModel: AllGuestsViewModel
    private lateinit var mListener : GuestListener
    private val mAdapter: GuestAdapter = GuestAdapter()
    private var _binding: FragmentAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
         allGuestsViewModel.text.observe(viewLifecycleOwner, Observer {
             textView.text = it
         })*/
        //RecyclerView
        // 1 Obter a recycler
        val recycler =  binding.recyclerAllGuests // binding //root.findViewById<RecyclerView>(R.id.recycler_all_guests) // findById
        // 2 Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)
        // 3 Definir um adapter
        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context,GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID,id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.EMPTY)
            }

        }
        mAdapter.attachListener(mListener)
        observer()
        mViewModel.load(GuestConstants.FILTER.EMPTY)


        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.EMPTY)
    }

    private fun observer(){
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}