package com.tom.codeforces.kotlinheroes.eight.contest

import kotlin.math.min

fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        val nbPairs = readLine()!!.toInt()
        val pairs = mutableSetOf<RhymePair>()
        repeat(nbPairs) {
            val (firstWord, secondWord, shouldRhyme) = readLine()!!.split(" ")
            pairs.add(RhymePair(firstWord, secondWord, shouldRhyme == "1"))
        }

        val shortestWordLength = pairs.filter { it.shouldRyme }.minOf { min(it.firstWord.length, it.secondWord.length) }

        val rhyming = mutableListOf<Int>()
        for (k in 1..shortestWordLength) {
            val notOkPairs = pairs.filter { !it.isOkAt(k) }
            if(notOkPairs.isEmpty()) {
                rhyming.add(k)
            } else if(notOkPairs.any { it.shouldRyme }) {
                break
            } else {
                notOkPairs.forEach { pairs.remove(it)}
            }
        }

        if (pairs.all { it.shouldRyme }) {
            rhyming.add(0)
        }

        println(rhyming.size)
        println(rhyming.joinToString(" "))
    }
}

data class RhymePair(val firstWord: String, val secondWord: String, val shouldRyme: Boolean) {
    var notRhyming: Boolean = false
    fun isOkAt(k: Int): Boolean {

        if (firstWord.length >= k
            && secondWord.length >= k
            && firstWord.substring(firstWord.length - k) == secondWord.substring(secondWord.length - k)
        ) {
            return shouldRyme
        }

        return !shouldRyme
    }
}
