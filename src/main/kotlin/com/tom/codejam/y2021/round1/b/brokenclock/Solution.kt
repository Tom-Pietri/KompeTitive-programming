package com.tom.codejam.y2021.round1.b.brokenclock

import java.math.BigInteger

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val (firstHand, secondHand, thirdHand) = readLine()!!.split(" ").map { it.toBigInteger() }
        val testCase = TestCase(firstHand, secondHand, thirdHand)
        println("Case #${i + 1}: ${computeSolution(testCase)}")
    }
}

val minuteNbDegreeNS = BigInteger("12")
val secondNbDegreeNS = BigInteger("720")
val secondNbDegree = BigInteger("720000000000")
val BIG_INT_60 = BigInteger("60")
val BIG_INT_60000000000 = BigInteger("60000000000")
val BIG_INT_3600000000000 = BigInteger("3600000000000")

fun computeSolution(input: TestCase): Solution {
    if (input.firstHand == input.secondHand && input.firstHand == input.thirdHand) {
        return Solution(0, 0, 0, 0)
    }

    var currentTestCase = input

    var totalRotation = BigInteger.ZERO
    while (true) {
        val potentialSolution = guess(currentTestCase.firstHand, currentTestCase.secondHand, currentTestCase.thirdHand)
            ?: guess(currentTestCase.thirdHand, currentTestCase.firstHand, currentTestCase.secondHand)
            ?: guess(currentTestCase.secondHand, currentTestCase.firstHand, currentTestCase.thirdHand)

        if (potentialSolution != null) {
            return potentialSolution
        }

        val rotationToTry = currentTestCase.minRotationForNextPotentialCase()
//        totalRotation += rotationToTry
        currentTestCase = currentTestCase.rotate(rotationToTry)
    }
}

fun guess(guessSeconds: BigInteger, firstHand: BigInteger, secondHand: BigInteger): Solution? {
    if(guessSeconds.mod(secondNbDegreeNS) != BigInteger.ZERO) {
        return null
    }

    val guessSecondsInNanoseconds = guessSeconds.divide(secondNbDegreeNS)
    if (guessSecondsInNanoseconds.nanoSecondToSecond() >= BigInteger("60")) {
        return null
    }

    return trySolution(guessSecondsInNanoseconds, firstHand, secondHand)
        ?: trySolution(guessSecondsInNanoseconds, secondHand, firstHand)
}

private fun trySolution(guessSecondsInNanoSecond: BigInteger, minutes: BigInteger, hours: BigInteger): Solution? {
    if (minutes.mod(minuteNbDegreeNS) != BigInteger.ZERO) {
        return null
    }

    val minuteInNanoSecond = minutes.minus(guessSecondsInNanoSecond.times(minuteNbDegreeNS)).divide(minuteNbDegreeNS)
    val hourInNanoSecond = hours.minus(minuteInNanoSecond).minus(guessSecondsInNanoSecond)

    return if (minuteInNanoSecond.nanoSecondToMinutes() < BIG_INT_60
        && minuteInNanoSecond.mod(BIG_INT_60000000000) == BigInteger.ZERO
        && hourInNanoSecond.mod(BIG_INT_3600000000000) == BigInteger.ZERO
    ) {
        Solution(
            hourInNanoSecond.nanoSecondToHour().toInt(),
            minuteInNanoSecond.nanoSecondToMinutes().toInt(),
            guessSecondsInNanoSecond.nanoSecondToSecond().toInt(),
            0
        )
    } else {
        null
    }
}

fun BigInteger.nanoSecondToSecond(): BigInteger = this.divide(BigInteger("1000000000"))
fun BigInteger.nanoSecondToMinutes(): BigInteger = this.nanoSecondToSecond().secondToMinute()
fun BigInteger.nanoSecondToHour(): BigInteger = this.nanoSecondToSecond().secondToMinute().secondToMinute()
fun BigInteger.secondToMinute(): BigInteger = this.divide(BigInteger("60"))
val maxRotation = BigInteger("43200000000000")

data class TestCase(val firstHand: BigInteger, val secondHand: BigInteger, val thirdHand: BigInteger) {
    fun minRotationForNextPotentialCase() : BigInteger {
        val firstHandMinimumRotation = secondNbDegree - firstHand.mod(secondNbDegree)
        val secondHandMinimumRotation = (secondNbDegree - secondHand.mod(secondNbDegree))
        val thirdHandMinimumRotation = (secondNbDegree - thirdHand.mod(secondNbDegree))

        return firstHandMinimumRotation.min(secondHandMinimumRotation).min(thirdHandMinimumRotation).max(BigInteger.ONE)
    }

    fun rotate(rotation: BigInteger) = TestCase(
        (firstHand + rotation).mod(maxRotation),
        (secondHand + rotation).mod(maxRotation),
        (thirdHand + rotation).mod(maxRotation)
    )
}

data class Solution(val hour: Int, val minute: Int, val second: Int, val nanosecond: Int) {

    override fun toString(): String {
        return "$hour $minute $second $nanosecond"
    }
}