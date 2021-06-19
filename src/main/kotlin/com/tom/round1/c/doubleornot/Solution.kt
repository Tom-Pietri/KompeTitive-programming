package com.tom.round1.c.doubleornot

import kotlin.math.max

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val (source, target) = readLine()!!.split(" ")
        println("Case #${i + 1}: ${computeSolution(TestCase(source, target)) ?: "IMPOSSIBLE"}")
    }
}

fun computeSolution(testCase: TestCase): Int? {
    if (testCase.source == testCase.target) {
        return 0
    }
    val stack = mutableListOf(Pair(testCase.source, 0))
    val allreadySeen = mutableSetOf(testCase.source)
    val max = max(testCase.target.length * 3, testCase.source.length * 3)

    do {
        val currentState = stack.removeFirst()
        val nottedCurrentState = currentState.first.not()
        val doubledCurrentState = currentState.first.double()
        if (nottedCurrentState == testCase.target || doubledCurrentState == testCase.target) {
            return currentState.second + 1
        }
        if (!allreadySeen.contains(nottedCurrentState)) {
            allreadySeen.add(nottedCurrentState)
            if (nottedCurrentState.length < testCase.target.length * 3) {
                stack.add(Pair(nottedCurrentState, currentState.second + 1))
            }
        }

        if (!allreadySeen.contains(doubledCurrentState)) {
            allreadySeen.add(doubledCurrentState)
            if (nottedCurrentState.length < max) {
                stack.add(Pair(doubledCurrentState, currentState.second + 1))
            }
        }
    } while (currentState.second < 100 && stack.isNotEmpty())


    return null
}

data class TestCase(val source: String, val target: String)

fun String.not(): String {
    return this.map {
        when (it) {
            '1' -> '0'
            '0' -> '1'
            else -> error("oups")
        }
    }.joinToString("").trimStart { it == '0' }
}

fun String.double(): String {
    return this + '0'
}