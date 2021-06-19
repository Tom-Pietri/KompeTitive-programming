package com.tom.codejam.y2021.round1.c.doubleornot

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DoubleOrNotingKtTest {

    @Test
    fun `first shoudl gk`() {
        val inputs = listOf(
            Pair(TestCase("10001", "111"), 4),
            Pair(TestCase("1011", "111"), 3),
            Pair(TestCase("1010", "1011"), 2),
            Pair(TestCase("0", "1"), 1),
            Pair(TestCase("0", "101"), null),
            Pair(TestCase("1101011", "1101011"), 0),
        )

        inputs.forEach {
           val computeSolution = computeSolution(it.first)
            expectThat(computeSolution).isEqualTo(it.second)
        }
    }

    @Test
    fun `asdf`() {
        val computed = computeSolution(TestCase("0", "101"))

        expectThat(computed).isEqualTo(null)
    }
}