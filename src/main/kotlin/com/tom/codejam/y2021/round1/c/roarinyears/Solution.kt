package com.tom.codejam.y2021.round1.c.roarinyears

import java.math.BigInteger

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val year = readLine()!!.toBigInteger()
        println("Case #${i + 1}: ${computeSolution(year)}")
    }
}

fun computeSolution(year: BigInteger): BigInteger {
    var nextYear = year + BigInteger.ONE
    while (true) {
        if (isRoaring(nextYear)) {
            return nextYear
        } else {
            nextYear += BigInteger.ONE
        }
    }
}

fun isRoaring(year: BigInteger): Boolean {

    val yearAsString = year.toString()
    for (i in 1 until (yearAsString.length / 2) + 1) {
        var stringSize = i
        var nextBreakpoint = computeNextBreakPoint(stringSize)

        val remainingChars = yearAsString.toMutableList()

        var previousNumber: BigInteger? = null

        var canBeRoaring = true
        while (canBeRoaring && remainingChars.isNotEmpty()) {
            var nextNumberAsString = ""
            repeat(stringSize) {
                val removeFirstOrNull = remainingChars.removeFirstOrNull()
                if (removeFirstOrNull == null) {
                    canBeRoaring = false
                } else {
                    nextNumberAsString += removeFirstOrNull
                }
            }
            if (!canBeRoaring) {
                break
            }
            if (nextNumberAsString.first() == '0') {
                canBeRoaring = false
            } else {
                val nextNumber = nextNumberAsString.toBigInteger()
                if (previousNumber == null || previousNumber + BigInteger.ONE == nextNumber) {
                    previousNumber = nextNumber
                    if (nextNumber == nextBreakpoint) {
                        stringSize++
                        nextBreakpoint = computeNextBreakPoint(stringSize)
                    }
                } else {
                    canBeRoaring = false
                }
            }

        }

        if (canBeRoaring) {
            return canBeRoaring
        }
    }

    return false
}

private fun computeNextBreakPoint(stringSize: Int): BigInteger = BigInteger.TEN.pow(stringSize) - BigInteger.ONE