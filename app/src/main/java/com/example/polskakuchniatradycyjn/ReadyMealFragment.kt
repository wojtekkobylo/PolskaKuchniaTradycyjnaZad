package com.example.polskakuchniatradycyjn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.polskakuchniatradycyjn.databinding.FragmentReadyMealBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReadyMealFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    
    private lateinit var binding: FragmentReadyMealBinding
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
        binding = FragmentReadyMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)

        binding.btnDealOne.setOnClickListener { viewModel.setDanie("Schabowy, puree ziemniaczane, surówka", 25.0)
        viewModel.setNapoj("kompot", price = 0.0)
        }
        binding.btnDealTwo.setOnClickListener { viewModel.setDanie("Pierś z kurczaka z frytkami", 25.0) }
        binding.btnDealThree.setOnClickListener { viewModel.setDanie("Pierogi z kapustą i grzybami", 25.0)
        viewModel.setNapoj("Piwo", 0.0)
        }
        binding.btnGoBack.setOnClickListener {
            viewModel.confirmOrder()
            findNavController().navigate(R.id.action_readyMealFragment_to_menuChoiceFragment)
        }


        binding.btnAddOrder.setOnClickListener {
            viewModel.confirmOrder()

            findNavController().navigate(R.id.action_readyMeal_to_summary)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReadyMealFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}