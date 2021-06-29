package com.tom.codeforces.kotlinheroes.seven.practice

fun main() {
    readLine()
    val array = readLine()!!.split(" ").map { it.toInt() }

    val max = array.maxOrNull()!!
    val aWithoutMax = array.filterNot { it == max }
    val sum = aWithoutMax.sum()
    val numberToRemove = sum - max
    val toRemove = mutableListOf<Int>()
    array.forEachIndexed { index, i ->
        if (i == numberToRemove && i != max) {
            toRemove += index + 1
        }
    }

    val secondMax = aWithoutMax.maxOrNull()!!
    if(secondMax == sum - secondMax) {
        array.forEachIndexed { index, i ->
            if(i == max) {
                toRemove += index + 1
            }
        }
    }

    println(toRemove.size)
    println(toRemove.joinToString(" "))
}
