package com.example.polskakuchniatradycyjn

data class PersonOrder(
    var zupa: String = "Brak",
    var zupaCena: Double = 0.0,
    var danie: String = "Brak",
    var danieCena: Double = 0.0,
    var napoj: String = "Brak",
    var napojCena: Double = 0.0
) {
    fun fullPrice(): Double {
        return zupaCena + danieCena + napojCena
    }
}