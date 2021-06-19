package com.tom.codejam.y2021.round1.a.appendsort

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class AppendSortKtTest {
    @Test
    fun `First sample input should return 4`() {
        val input = listOf(100, 7, 10)

        expectThat(computeSolution(input.map { it.toBigInteger() })).isEqualTo(4)
    }

    @Test
    fun `Second sample input should return 1`() {
        val input = listOf(10, 10)

        expectThat(computeSolution(input.map { it.toBigInteger() })).isEqualTo(1)
    }

    @Test
    fun `Third sample input should return 2`() {
        val input = listOf(3, 19, 1)

        expectThat(computeSolution(input.map { it.toBigInteger() })).isEqualTo(2)
    }

    @Test
    fun `Fourth sample input should return 0`() {
        val input = listOf(1, 2, 3)

        expectThat(computeSolution(input.map { it.toBigInteger() })).isEqualTo(0)
    }

    @Test
    fun `Fith sample input should return 3`() {
        val input = listOf(3, 2, 1)

        expectThat(computeSolution(input.map { it.toBigInteger() })).isEqualTo(3)
    }


    @Test
    fun `Six sample input should return 5`() {
        val input = listOf(333, 2, 100)

        expectThat(computeSolution(input.map { it.toBigInteger() })).isEqualTo(5)
    }
}