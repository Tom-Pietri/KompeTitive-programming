package com.tom.codejam.y2021.qualification.reversesort

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ReverseSortSolutionTest {


    @Test
    fun `Case 1 should return 6`() {
        val case1 = listOf(4, 2, 1, 3)

        val result = computeSolution(case1)

        expectThat(result).isEqualTo(6)

    }

    @Test
    fun `Case 2 should return 1`() {
        val case1 = listOf(4, 2, 1, 3)

        val result = computeSolution(case1)

        expectThat(result).isEqualTo(6)
    }


    @Test
    fun `Case 3 should return 12`() {
        val case1 = listOf(4, 2, 1, 3)

        val result = computeSolution(case1)

        expectThat(result).isEqualTo(6)
    }
}