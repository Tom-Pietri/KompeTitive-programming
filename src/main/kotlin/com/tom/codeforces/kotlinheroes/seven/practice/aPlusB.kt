package com.tom.codeforces.kotlinheroes.seven.practice

fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        println(readLine()!!.split(" ").map { it.toInt() }.sum())
    }
}