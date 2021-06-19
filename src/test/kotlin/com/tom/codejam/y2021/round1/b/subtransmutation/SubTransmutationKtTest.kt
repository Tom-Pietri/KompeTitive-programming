package com.tom.codejam.y2021.round1.b.subtransmutation

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


internal class SubTransmutationKtTest {

    @Test
    fun `First test case should return 4`() {
        val testCase = TestCase(2, Spell(1, 2), listOf(RequierdMaterial(1, 1), RequierdMaterial(2, 2)))

        val computeSolution = computeSolution(testCase)

        expectThat(computeSolution).isEqualTo(4)
    }

    @Test
    fun `Second test case should return 4`() {
        val testCase = TestCase(5, Spell(1, 2), listOf(RequierdMaterial(2, 1), RequierdMaterial(1, 5)))

        val computeSolution = computeSolution(testCase)

        expectThat(computeSolution).isEqualTo(6)
    }

    @Test
    fun `Third test case should return 4`() {
        val testCase = TestCase(
            largestMetalToCreate = 3,
            spell = Spell(1, 2),
            materials = listOf(RequierdMaterial(1, 1), RequierdMaterial(1, 2), RequierdMaterial(1, 3))
        )

        val computeSolution = computeSolution(testCase)

        expectThat(computeSolution).isEqualTo(5)
    }

    // PART 2

    @Test
    fun `Fourth test case should return null`() {
        val testCase = TestCase(
            largestMetalToCreate = 3,
            spell = Spell(2, 4),
            materials = listOf(RequierdMaterial(1, 1), RequierdMaterial(1, 2), RequierdMaterial(1, 3))
        )

        val computeSolution = computeSolution(testCase)

        expectThat(computeSolution).isEqualTo(null)
    }

    @Test
    fun `Fith test case should return 5`() {
        val testCase = TestCase(
            largestMetalToCreate = 3,
            spell = Spell(2, 4),
            materials = listOf(RequierdMaterial(1, 1), RequierdMaterial(1, 3))
        )

        val computeSolution = computeSolution(testCase)

        expectThat(computeSolution).isEqualTo(5)
    }

    @Test
    fun `Sixth test case should return 10`() {
        val testCase = TestCase(
            largestMetalToCreate = 5,
            spell = Spell(2, 5),
            materials = listOf(RequierdMaterial(1, 1), RequierdMaterial(1, 5))
        )

        val computeSolution = computeSolution(testCase)

        expectThat(computeSolution).isEqualTo(10)
    }
}