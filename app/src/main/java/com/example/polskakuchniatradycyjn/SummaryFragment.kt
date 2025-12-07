package com.example.polskakuchniatradycyjn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.polskakuchniatradycyjn.databinding.FragmentSummaryBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SummaryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    
    private lateinit var binding: FragmentSummaryBinding
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
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)

        viewModel.allOrders.observe(viewLifecycleOwner) { list ->
            binding.ordersContainer.removeAllViews()
            
            var index = 0
            for (order in list) {

                val orderView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, null)
                val text1 = orderView.findViewById<TextView>(android.R.id.text1)
                val text2 = orderView.findViewById<TextView>(android.R.id.text2)
                
                text1.text = "Zestaw ${index + 1}: ${order.fullPrice()} zł"
                text2.text = "${order.zupa}, ${order.danie}, ${order.napoj}"
                

                val removeBtn = Button(context)
                removeBtn.text = "Usuń"
                removeBtn.textSize = 10f
                val currentIndex = index
                removeBtn.setOnClickListener {
                    viewModel.removeOrder(currentIndex)
                }

                binding.ordersContainer.addView(orderView)
                binding.ordersContainer.addView(removeBtn)
                
                index++
            }
        }

        binding.btnNewOrder.setOnClickListener {

            findNavController().navigate(R.id.menuChoiceFragment)
        }

        binding.btnClear.setOnClickListener {
            viewModel.clearAll()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SummaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}