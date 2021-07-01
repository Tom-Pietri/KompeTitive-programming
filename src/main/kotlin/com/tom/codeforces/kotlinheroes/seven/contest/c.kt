package com.tom.codeforces.kotlinheroes.seven.contest

fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }

        val sweets = readLine()!!.map { Character.getNumericValue(it) }.mapIndexed {index, i -> index + 1 to i }.toMutableList()

        var eaten = 0
        var currentIndex = 0
        while(sweets.any { it.second == 1 }) {
            eaten++
            sweets.removeAt(currentIndex)
            if(sweets.size > 0) {
                currentIndex = ((currentIndex - 1 + k) % sweets.size)
            }
        }

        println(eaten)
    }
}
