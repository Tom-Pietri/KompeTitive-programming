package com.tom.round1.c.roarinyears

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigInteger

internal class RoaringYearsKtTest {
    @Test
    fun `first test case should return 2021`() {
        val solution = computeSolution(BigInteger("2020"))

        expectThat(solution).isEqualTo(BigInteger("2021"))
    }

    @Test
    fun `second test case should return 2122`() {
        val solution = computeSolution(BigInteger("2021"))

        expectThat(solution).isEqualTo(BigInteger("2122"))
    }

    @Test
    fun `second test case should return 78910`() {
        val solution = computeSolution(BigInteger("68000"))

        expectThat(solution).isEqualTo(BigInteger("78910"))
    }


    @Test
    fun `second test case should return 123`() {
        val solution = computeSolution(BigInteger("101"))

        expectThat(solution).isEqualTo(BigInteger("123"))
    }

    @Test
    fun `BigNumber test case should return 10001001`() {
        val solution = computeSolution(BigInteger("9999999"))
//        106
        //100000
//        123455
        expectThat(solution).isEqualTo(BigInteger("10001001"))
    }

//    @Test
//    fun `Test all test case should return 10001001`() {
//        for (i in 0..9999999) {
//            val solution = computeSolution(BigInteger(i.toString()))
//            println("$i $solution")
////            expectThat(solution).isEqualTo(BigInteger("10001001"))
//        }
//    }
}

