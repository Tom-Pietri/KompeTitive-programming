package com.tom.round1.c.closestpick

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.File
import java.math.BigDecimal
import java.math.BigInteger

internal class ClosestPickKtTest {
    @Test
    fun `asdf`() {
        val input = listOf(
            Pair(
                TestCase(BigInteger("3"), BigInteger("10"), listOf(BigInteger("1"), BigInteger("3"), BigInteger("7"))),
                BigDecimal("0.5")
            ),
            Pair(
                TestCase(
                    BigInteger("4"),
                    BigInteger("10"),
                    listOf(BigInteger("4"), BigInteger("1"), BigInteger("7"), BigInteger("3"))
                ), BigDecimal("0.4")
            ),
            Pair(
                TestCase(
                    BigInteger("4"),
                    BigInteger("3"),
                    listOf(BigInteger("1"), BigInteger("2"), BigInteger("3"), BigInteger("2"))
                ), BigDecimal("0")
            ),
            Pair(
                TestCase(
                    BigInteger("4"),
                    BigInteger("4"),
                    listOf(BigInteger("1"), BigInteger("2"), BigInteger("4"), BigInteger("2"))
                ), BigDecimal("0.25")
            ),
            Pair(
                TestCase(
                    BigInteger("1"),
                    BigInteger("4"),
                    listOf(BigInteger("2"))
                ), BigDecimal("0.25")
            )
        )

        input.forEach {
            val computeSolution = computeSolution(it.first)
            expectThat(computeSolution).isEqualTo(it.second)
        }
    }

    @Test
    fun `run testInput`() {
        val testFileInput = File("./src/main/resources/ts1_input.txt").bufferedReader()

        val nbOfCase = testFileInput.readLine()!!.toInt()

        for (i in 0 until nbOfCase) {
            val (source, target) = testFileInput.readLine()!!.split(" ").map { it.toBigInteger() }
            val allreadySoldTickets = testFileInput.readLine()!!.split(" ").map { it.toBigInteger() }
            println("Case #${i + 1}: ${computeSolution(TestCase(source, target, allreadySoldTickets))}")
        }
    }
}

