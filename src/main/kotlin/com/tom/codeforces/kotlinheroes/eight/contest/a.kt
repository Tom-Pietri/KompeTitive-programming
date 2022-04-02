package com.tom.codeforces.kotlinheroes.eight.contest

fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        var comparisons = readLine()!!.toCharArray()

        if (comparisons.all { it == '=' }) {
            println("=")
        } else if(comparisons.contains('<') && comparisons.contains('>')) {
            println("?")
        } else if(comparisons.filterNot { it == '=' }.all { it == '<' }) {
            println("<")
        } else if(comparisons.filterNot { it == '=' }.all { it == '>' }) {
            println(">")
        } else {
            println("?")
        }
    }
}