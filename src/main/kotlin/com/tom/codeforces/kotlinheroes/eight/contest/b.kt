package com.tom.codeforces.kotlinheroes.eight.contest


fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        val nbVolumes = readLine()!!.toInt()
        val (dayFirstVolume, firstVolume) = readLine()!!.split(" ").map { it.toInt() }
        val (daySecondVolume, secondVolume) = readLine()!!.split(" ").map { it.toInt() }
        val daySearched = readLine()!!.toInt()
        if(firstVolume == secondVolume) {
            println(firstVolume)
        } else {
            val nbPossibleVolumes = secondVolume - firstVolume
            val nbDaysBeforeDaySearched = daySearched - dayFirstVolume
            if (nbPossibleVolumes < nbDaysBeforeDaySearched) {
                println(secondVolume)
            } else {
                println(firstVolume + nbDaysBeforeDaySearched)
            }
        }
    }
}