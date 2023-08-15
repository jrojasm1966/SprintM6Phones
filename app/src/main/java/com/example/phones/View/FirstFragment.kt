package com.example.phones.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phones.PhonesAdapter
import com.example.phones.R
import com.example.phones.ViewModel.PhonesViewModel
import com.example.phones.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var  mBinding : FragmentFirstBinding
    private val mViewModel : PhonesViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DEBEMOS INSTANCIAR ADAPTER
        val adapter = PhonesAdapter()
        mBinding.recyclerView.adapter= adapter
        mBinding.recyclerView.layoutManager= LinearLayoutManager(context)
        mViewModel.getPhonesList().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }

        })

        // MÉTODO PARA SELECCIONAR
        adapter.selectedPhone().observe(viewLifecycleOwner, Observer {
            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion", it.id.toString())

            }
            val bundle = Bundle().apply {
                putString("courseId", it.id.toString())
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}