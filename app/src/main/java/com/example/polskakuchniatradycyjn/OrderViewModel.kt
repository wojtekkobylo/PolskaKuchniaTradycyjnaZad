package com.example.polskakuchniatradycyjn;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {

    val currentOrder = MutableLiveData<PersonOrder>()
    val allOrders = MutableLiveData<MutableList<PersonOrder>>()
    val currentSum = MutableLiveData<Double>()
    val totalSum = MutableLiveData<Double>()

    init {
        currentOrder.value = PersonOrder()
        allOrders.value = mutableListOf()
        currentSum.value = 0.0
        totalSum.value = 0.0
    }

    fun setZupa(name: String, price: Double) {
        val order = currentOrder.value!!
        order.zupa = name
        order.zupaCena = price
        updateCurrentSum()
    }

    fun setDanie(name: String, price: Double) {
        val order = currentOrder.value!!
        order.danie = name
        order.danieCena = price
        updateCurrentSum()
    }

    fun setNapoj(name: String, price: Double) {
        val order = currentOrder.value!!
        order.napoj = name
        order.napojCena = price
        updateCurrentSum()
    }

    private fun updateCurrentSum() {
        val order = currentOrder.value!!
        currentSum.value = order.fullPrice()
        currentOrder.value = order // trigger observer
    }

    fun confirmOrder() {
        val list = allOrders.value!!
        list.add(currentOrder.value!!)
        allOrders.value = list
        
        recalculateTotal()

        currentOrder.value = PersonOrder()
        currentSum.value = 0.0
    }

    fun removeOrder(index: Int) {
        val list = allOrders.value!!
        if (index >= 0 && index < list.size) {
            list.removeAt(index)
            allOrders.value = list
            recalculateTotal()
        }
    }

    private fun recalculateTotal() {
        var sum = 0.0
        val list = allOrders.value!!
        for (o in list) {
            sum += o.fullPrice()
        }
        totalSum.value = sum
    }

    fun clearAll() {
        allOrders.value = mutableListOf()
        totalSum.value = 0.0
        currentOrder.value = PersonOrder()
        currentSum.value = 0.0
    }
}