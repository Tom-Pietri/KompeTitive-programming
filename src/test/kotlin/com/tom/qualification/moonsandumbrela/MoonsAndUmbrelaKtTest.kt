package com.tom.qualification.moonsandumbrela

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MoonsAndUmbrelaKtTest {

    @Test
    fun `Test case 1 must return 5`() {
        val input = InputCase(2, 3, "CJ?CC?")

        val result = computeSolution(input)

        expectThat(result).isEqualTo(5)
    }

    @Test
    fun `Test case 2 must return 10`() {
        val input = InputCase(4, 2, "CJCJ")

        val result = computeSolution(input)

        expectThat(result).isEqualTo(10)
    }

    @Test
    fun `Test case 3 must return 1`() {
        val input = InputCase(1 ,3, "C?J")

        val result = computeSolution(input)

        expectThat(result).isEqualTo(1)
    }

    @Test
    fun `Test case 4 must return 0`() {
        val input = InputCase(2, 5, "??J???")

        val result = computeSolution(input)

        expectThat(result).isEqualTo(0)
    }


    @Test
    fun `Test case 5 must return -8`() {
        val input = InputCase(2, -5, "??JJ??")

        val result = computeSolution(input)

        expectThat(result).isEqualTo(-8)
    }


    @Test
    fun `Test case 6 must return -11`() {
        val input = InputCase(2, -5, "??JJ???")

        val result = computeSolution(input)

        expectThat(result).isEqualTo(-11)
    }
}