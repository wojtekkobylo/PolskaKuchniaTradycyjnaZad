package com.example.polskakuchniatradycyjn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.polskakuchniatradycyjn.databinding.FragmentTotalBarBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TotalBarFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentTotalBarBinding
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTotalBarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)

        viewModel.currentSum.observe(viewLifecycleOwner) { sum ->
            binding.txtCurrentSum.text = "Bieżące: " + sum + " zł"
        }

        viewModel.totalSum.observe(viewLifecycleOwner) { sum ->
            binding.txtTotalSum.text = "Razem: " + sum + " zł"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TotalBarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}