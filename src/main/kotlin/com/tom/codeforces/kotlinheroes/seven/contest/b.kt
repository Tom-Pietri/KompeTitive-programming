package com.tom.codeforces.kotlinheroes.seven.contest

fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        val n = readLine()!!.toInt()
        val integers = readLine()!!.split(" ").map { it.toInt() }
        var canFail = false;
        integers.zipWithNext().forEach {
            if((it.second - it.first) % 2 == 0) {
                canFail = true
            }
        }

        if (canFail) {
            println("YES")
        } else {
            println("NO")
        }
    }
}
