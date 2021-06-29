package com.tom.codeforces.kotlinheroes.seven.practice

fun main() {
    readLine()
    val skills = readLine()!!.split(" ").map { it.toInt() }.sorted()
    println(skills.chunked(2).sumOf { it[1] - it[0] })
}