package com.tom.round1.a.primetime

import java.math.BigInteger

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val nbPrimes = readLine()!!.toInt()
        val input = mutableListOf<Int>()
        for (j in 0 until nbPrimes) {
            val (prime, nbCards) = readLine()!!.split(" ").map { it.toInt() }
            repeat(nbCards) { input.add(prime) }
        }

        println("Case #${i + 1}: ${computeSolution(input)}")
    }
}

fun computeSolution(input: List<Int>): Int {
    return generatePossibleCombinations(input)
        .filter { it.isValid() }
        .maxByOrNull { it.score }?.score ?: 0
}

// see https://stackoverflow.com/a/29656792
fun generatePossibleCombinations(input: List<Int>): List<Combination> {
    val combinations = mutableListOf<Combination>()
    val flags = BooleanArray(input.size)
    var i = 0
    while (i != input.size) {
        val a = mutableListOf<Int>()
        val b = mutableListOf<Int>()
        for (j in input.indices) if (flags[j]) a.add(input[j]) else b.add(input[j])

        combinations.add(Combination(sums = a, times = b))
        i = 0
        while (i < input.size) {
            flags[i] = !flags[i]
            if(flags[i]) {
                break
            }
            i++
        }
    }

    return combinations
}


data class Combination(val sums: List<Int>, val times: List<Int>) {
    fun isValid(): Boolean {
        if(sums.isEmpty() || times.isEmpty()) return false

        return sums.map { it.toBigInteger() }.reduce(BigInteger::add) == times.map { it.toBigInteger() }.reduce(BigInteger::times)
    }

    val score = sums.sum()
}