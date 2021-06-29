package com.tom.codeforces.kotlinheroes.seven.practice

fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val minFreq = n / k
        var remaining = n % k

        var output = ""
        for (i in 0 until k) {
            repeat(minFreq) {
                output +=  ('a' + i).toString()
            }
            if(remaining > 0) {
                output +=  ('a' + i).toString()
                remaining--
            }
        }
        println(output)
    }
}