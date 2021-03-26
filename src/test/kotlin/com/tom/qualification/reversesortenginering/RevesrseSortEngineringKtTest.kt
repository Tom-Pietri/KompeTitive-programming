package com.tom.qualification.reversesortenginering

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class RevesrseSortEngineringKtTest {

    @Test
    fun `Test case 1 must return 4 2 1 3`() {
        val input = InputCase(4, 6)

        val solution = computeSolution(input)

        expectThat(solution).isEqualTo(listOf(4, 3, 2, 1))
    }

    @Test
    fun `Test case 2 must return 1 2`() {
        val input = InputCase(2, 1)

        val solution = computeSolution(input)

        expectThat(solution).isEqualTo(listOf(1, 2))
    }

    @Test
    fun `Test case 3 must return 7 6 5 4 3 2 1`() {
        val input = InputCase(7, 12)

        val solution = computeSolution(input)

        expectThat(solution).isEqualTo(listOf(7, 6, 5, 4, 3, 2, 1))
    }

    @Test
    fun `Test case 4 must return null`() {
        val input = InputCase(7, 2)

        val solution = computeSolution(input)

        expectThat(solution).isEqualTo(null)
    }

    @Test
    fun `Test case 5 must return null`() {
        val input = InputCase(2, 1000)

        val solution = computeSolution(input)

        expectThat(solution).isEqualTo(null)
    }

    @Test
    fun `Test case 6 must return 2 4 3 1`() {
        val input = InputCase(4, 9)

        val solution = computeSolution(input)

        expectThat(solution).isEqualTo(listOf(2, 4, 3, 1))
        // 2 3 4 1
        // 1 4 3 2 -> 4
        // 1 2 3 4 -> 3
        // 1 2 3 4 -> 1

        // 2 4 3 1
        // 1 3 4 2 -> 4
        // 1 2 4 3 -> 3
        // 1 2 3 4 -> 2
    }

}