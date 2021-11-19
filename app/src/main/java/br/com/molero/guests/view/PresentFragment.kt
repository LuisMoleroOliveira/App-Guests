package br.com.molero.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.molero.guests.databinding.FragmentPresentBinding
import br.com.molero.guests.service.constants.GuestConstants
import br.com.molero.guests.view.adapter.GuestAdapter
import br.com.molero.guests.view.listener.GuestListener
import br.com.molero.guests.viwmodel.AllGuestsViewModel

class PresentFragment : Fragment() {

    private lateinit var mViewModel: AllGuestsViewModel
    private lateinit var mListener: GuestListener
    private val mAdapter: GuestAdapter = GuestAdapter()
    private var _binding: FragmentPresentBinding? = null

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

        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 1 Obter a recycler
        val recycler =
            binding.recyclerFragmentPresent // binding //root.findViewById<RecyclerView>(R.id.recycler_all_guests) // findById
        // 2 Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)
        // 3 Definir um adapter
        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)
            }

        }
        mAdapter.attachListener(mListener)
        observer()
        mViewModel.load(GuestConstants.FILTER.PRESENT)

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENT)
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