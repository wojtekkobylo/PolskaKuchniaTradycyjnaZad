package com.example.polskakuchniatradycyjn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.polskakuchniatradycyjn.databinding.FragmentCustomMealBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CustomMealFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    
    private lateinit var binding: FragmentCustomMealBinding
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
        binding = FragmentCustomMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)


        binding.btnSoup1.setOnClickListener { viewModel.setZupa("Żurek", 15.0) }
        binding.btnSoup2.setOnClickListener { viewModel.setZupa("Barszcz", 10.0) }
        
        binding.btnNext1.setOnClickListener {
            binding.stageSoup.visibility = View.GONE
            binding.stageMain.visibility = View.VISIBLE
        }


        binding.btnMain1.setOnClickListener { viewModel.setDanie("Golonka", 35.0) }
        binding.btnMain2.setOnClickListener { viewModel.setDanie("Placki", 18.0) }

        binding.btnNext2.setOnClickListener {
            binding.stageMain.visibility = View.GONE
            binding.stageDrink.visibility = View.VISIBLE
        }


        binding.btnDrink1.setOnClickListener { viewModel.setNapoj("Piwo", 10.0) }
        binding.btnDrink2.setOnClickListener { viewModel.setNapoj("Cola", 6.0) }

        binding.btnAddOrder.setOnClickListener {
            viewModel.confirmOrder()
            findNavController().navigate(R.id.action_customMeal_to_summary)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomMealFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}