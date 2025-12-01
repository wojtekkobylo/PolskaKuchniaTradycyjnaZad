package com.example.polskakuchniatradycyjn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel : ViewModel(){

    private val _order = MutableLiveData<Order>()

    public val order: LiveData<Order> get() = _order

    fun newOrder(
        zupa: String = "brak",
        danie: String = "brak",
        napój: String = "brak",
        cena: Double = 0.0,
        

    ) {

        _order.value = Order(zupa,danie,napój,cena)
    }


}