package com.tom.kickstart.y2020.roundc.aliengenerator

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigInteger

internal class SolutionKtTest {
    @Test
    fun `Samples`() {
        val inputs = listOf(Pair(BigInteger("10"), 2), Pair(BigInteger("125"), 4))
//        val inputs = listOf(Pair(BigInteger("125"), 4))
        inputs.forEach {
            val solution = computeSolution(it.first)
            expectThat(solution).isEqualTo(it.second)
        }
    }


    @Test
    fun `Testing stuff`() {
        val inputs = listOf(Pair(BigInteger("5244567812"), 5))
        inputs.forEach {
            val solution = computeSolution(it.first)
            expectThat(solution).isEqualTo(it.second)
        }
    }

}