package com.tom.codejam.y2021.round1.b.brokenclock

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import java.math.BigInteger
import kotlin.random.Random

internal class BrokenClockKtTest {
    @Test
    fun `first test case should give 0 0 0 0`() {
        val testCase = TestCase(BigInteger("0"), BigInteger("0"), BigInteger("0"))

        val solution = computeSolution(testCase)

        expectThat(solution).isEqualTo(Solution(0, 0, 0, 0))
    }

    @Test
    fun `second test case should give 6 30 0 0`() {

        val testCase = TestCase(BigInteger("0"), BigInteger("21600000000000"), BigInteger("23400000000000"))

        val solution = computeSolution(testCase)

        expectThat(solution).isEqualTo(Solution(6, 30, 0, 0))
    }

    @Test
    fun `third test case should give 1 2 3 0`() {
        val testCase = TestCase(BigInteger("1476000000000"), BigInteger("2160000000000"), BigInteger("3723000000000"))

        val solution = computeSolution(testCase)

        expectThat(solution).isEqualTo(Solution(1, 2, 3, 0))
    }

    @Test
    fun `fourth test case should give 0 0 0 0`() {
        val testCase = TestCase(BigInteger("5400000000000"), BigInteger("5400000000000"), BigInteger("5400000000000"))

        val solution = computeSolution(testCase)

        expectThat(solution).isEqualTo(Solution(0, 0, 0, 0))
    }

    @Test
    fun `fith test case should give 0 30 0 0`() {
        val testCase = TestCase(BigInteger("10800000000000"), BigInteger("32400000000000"), BigInteger("34200000000000"))

        val solution = computeSolution(testCase)

        expectThat(solution).isEqualTo(Solution(0, 30, 0, 0))
    }

    @Test
    fun `Sixth test case should give 1 2 3 0`() {
        val testCase = TestCase(BigInteger("23076000000000"), BigInteger("23760000000000"), BigInteger("25323000000000"))

        val solution = computeSolution(testCase)

        expectThat(solution).isEqualTo(Solution(1, 2, 3, 0))
    }

    @Test
    fun `generated test case should give expectedResult`() {

        repeat (10000) {
            val nbHour = Random.nextInt(60)
            val nbMinute = Random.nextInt(60)
            val nbSecond = Random.nextInt(60)

            val secondAsMillis = nbSecond.toBigInteger().times(BigInteger("1000000000"))
            val minutesAsMillis = nbMinute.toBigInteger()
                .times(BigInteger("60"))
                .times(BigInteger("1000000000"))
            val hoursAsMillis = nbHour.toBigInteger()
                .times(BigInteger("60"))
                .times(BigInteger("60"))
                .times(BigInteger("1000000000"))

            val secondsAsTick = secondAsMillis.times(BigInteger("720"))
            val minuteAsTick = minutesAsMillis.times(BigInteger("12")).plus(secondAsMillis.times(BigInteger("12")))
            val hoursAsTick = hoursAsMillis.plus(secondAsMillis).plus(minutesAsMillis)

            val testCase = TestCase(hoursAsTick, minuteAsTick, secondsAsTick)

            try {
                val solution = computeSolution(testCase)
                if (solution.hour != nbHour || solution.minute != nbMinute || solution.second != nbSecond) {
                    println("${nbHour} ${nbMinute} ${nbSecond}")
                    println("${solution.hour} ${solution.minute} ${solution.second}")
                    println("${testCase.firstHand} ${testCase.secondHand} ${testCase.thirdHand}")
                    expectThat(true).isFalse()
                }
            } catch (e : IllegalStateException) {

                println("${nbHour} ${nbMinute} ${nbSecond}")
                println("${testCase.firstHand} ${testCase.secondHand} ${testCase.thirdHand}")
            }
        }
    }

//    @Test
//    fun `run case 1`() {
//        val testFileInput = File("./src/com.tom.battledev.y2021.problem3.com.tom.battledev.y2021.problem4.com.tom.battledev.y2021.problem5.main/resources/ts1_input.txt").bufferedReader()
//        val testFileOutput = File("./src/com.tom.battledev.y2021.problem3.com.tom.battledev.y2021.problem4.com.tom.battledev.y2021.problem5.main/resources/ts1_output.txt").bufferedReader()
//
//
//        val nbOfCase = testFileInput.readLine()!!.toInt()
//
//        for (i in 0 until nbOfCase) {
//            val (firstHand, secondHand, thirdHand) = testFileInput.readLine()!!.split(" ").map { it.toBigInteger() }
//            val testCase = TestCase(firstHand, secondHand, thirdHand)
//            val expected = testFileOutput.readLine().split(": ")[1].split(" ").map { it.toInt() }.let {
//                Solution(it[0], it[1], it[2], it[3])
//            }
//            val solution = computeSolution(testCase)
//
//            println("Case #${i + 1}: $solution")
//            println("Case #${i + 1}: $expected")
//            expectThat(solution).isEqualTo(expected)
//
//        }
//
//    }
}