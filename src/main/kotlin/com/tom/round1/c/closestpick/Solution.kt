package com.tom.round1.c.closestpick

import java.math.BigDecimal
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO
import java.math.RoundingMode

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val (source, target) = readLine()!!.split(" ").map { it.toBigInteger() }
        val allreadySoldTickets = readLine()!!.split(" ").map { it.toBigInteger() }
        println("Case #${i + 1}: ${computeSolution(TestCase(source, target, allreadySoldTickets))}")
    }
}

fun computeSolution(testCase: TestCase): BigDecimal? {
    val sorted = testCase.allreadySoldTickets.sorted()

    val ranges = mutableListOf<BigInteger>()
    for (i in 0 until sorted.size - 1) {
        ranges.add((sorted[i + 1] - sorted[i]).divide(BigInteger("2")))
    }

    if (!sorted.contains(ONE)) {
        ranges.add(sorted.first() - ONE)
    }

    if (!sorted.contains(testCase.maxTicket)) {
        ranges.add(testCase.maxTicket - sorted.last())
    }

    val biggest = ranges.sorted().takeLast(2)

    return (BigDecimal(biggest.firstOrNull() ?: ZERO, 6) + BigDecimal(biggest.getOrNull(1) ?: ZERO, 6))
        .divide(BigDecimal(testCase.maxTicket, 6), RoundingMode.HALF_EVEN)
}

data class TestCase(val nbTicketSold: BigInteger, val maxTicket: BigInteger, val allreadySoldTickets: List<BigInteger>)
