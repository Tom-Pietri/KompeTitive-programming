package com.tom.codejam.y2021.round1.a.primetime

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.File


internal class SolutionKtTest {
    @Test
    fun `first test case should return 25`() {
        val input = listOf(2, 2, 3, 5, 5, 7, 11)

        expectThat(computeSolution(input)).isEqualTo(25)
    }

    @Test
    fun `first test case should return 17`() {
        val input = listOf(17, 17)

        expectThat(computeSolution(input)).isEqualTo(17)
    }

    @Test
    fun `third test case should return 0`() {
        val input = listOf(2, 2, 3)

        expectThat(computeSolution(input)).isEqualTo(0)
    }

    @Test
    fun `fourth test case should return 8`() {
        val input = listOf(2, 2, 2, 2, 2, 2, 2)

        expectThat(computeSolution(input)).isEqualTo(8)
    }

    @Test
    fun `fourth test case should return x`() {
        val testFileInput = File("./src/com.tom.battledev.y2021.problem3.com.tom.battledev.y2021.problem4.com.tom.battledev.y2021.problem5.main/resources/ts1_input.txt").bufferedReader()
        val testFileOutput = File("./src/com.tom.battledev.y2021.problem3.com.tom.battledev.y2021.problem4.com.tom.battledev.y2021.problem5.main/resources/ts1_output.txt").bufferedReader()

        val nbOfCase = testFileInput.readLine()!!.toInt()

        for (i in 0 until nbOfCase) {
            val nbPrimes = testFileInput.readLine()!!.toInt()
            val input = mutableListOf<Int>()
            for (j in 0 until nbPrimes) {
                val (prime, nbCards) = testFileInput.readLine()!!.split(" ").map { it.toInt() }
                repeat(nbCards) { input.add(prime) }
            }

            val expected = testFileOutput.readLine().split(":")[1].trim().toInt()
            val solution = computeSolution(input)
            expectThat(solution).isEqualTo(expected)
            println("Case #${i + 1}: $solution")
        }

//        expectThat(computeSolution(input)).isEqualTo(625)
    }


}