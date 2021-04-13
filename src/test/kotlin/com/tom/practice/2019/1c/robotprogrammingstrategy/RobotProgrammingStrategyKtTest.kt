package com.tom.practice.`2019`.`1c`.robotprogrammingstrategy

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class RobotProgrammingStrategyKtTest {
    @Test
    fun `First test case should return P`() {
        val input = listOf("RS")

        expectThat(computeSolution(input)).isEqualTo("P")
    }

    @Test
    fun `Second test case should return null`() {
        val input = listOf("R", "P", "S")

        expectThat(computeSolution(input)).isEqualTo("IMPOSSIBLE")
    }


    @Test
    fun `Third test case should return P`() {
        val input = listOf("RS", "RS", "RS", "RS", "RS", "RS", "RS")

        expectThat(computeSolution(input)).isEqualTo("P")
    }

    @Test
    fun `Fourth test case should return SR`() {
        val input = listOf("S", "P")

        expectThat(computeSolution(input)).isEqualTo("SR")
    }

}