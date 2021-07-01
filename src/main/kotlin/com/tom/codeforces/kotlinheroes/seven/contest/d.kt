package com.tom.codeforces.kotlinheroes.seven.contest

// Finished after the
fun main() {
    val (n, _) = readLine()!!.split(" ").map { it.toInt() }

    val strings = mutableSetOf<String>()
    repeat(n) {
        strings.add(readLine()!!)
    }

    val q = readLine()!!.toInt()
    repeat(q) {
        val query = readLine()!!
        val found = query.mapIndexed { index, c -> query.removeRange(index until index + 1) }
            .toSet()
            .filter { strings.contains(it) }.size
        println(found)
    }
}