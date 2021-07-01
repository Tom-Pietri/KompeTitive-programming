package com.tom.codeforces.kotlinheroes.seven.contest

import kotlin.math.max

fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val friends = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until n) {
            val (l, r) = readLine()!!.split(" ").map { it.toInt() }
            friends.add(Pair(l, r))
        }
        val nbDays = friends.filter { it.second >= k }
            .filter { it.first <= k }.map { Pair(max(it.first, k), it.second) }.map { it.second - it.first + 1 }
            .maxOrNull() ?: 0
        println(nbDays)
    }
}
