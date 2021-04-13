package com.tom.round1.a.appendsort

import java.math.BigInteger

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        readLine()
        val numbersToSort = readLine()!!.split(" ").map { BigInteger(it) }
        println("Case #${i + 1}: ${computeSolution(numbersToSort)}")
    }
}

fun computeSolution(input: List<BigInteger>): Int {
    val mutableInput = input.toMutableList()
    var nbNumbersAdded = 0

    for (i in 1 until mutableInput.size) {
        var curentNumberToSort = mutableInput[i]
        val previousNumber = mutableInput[i - 1]

        if(curentNumberToSort <= previousNumber) {
            val lengthDifference = previousNumber.toString().length - curentNumberToSort.toString().length
            val currentNumberWithNines = curentNumberToSort.toString().padEnd(previousNumber.toString().length, '9').toBigInteger()
            val currentNumberWithZeroes = curentNumberToSort.toString().padEnd(previousNumber.toString().length, '0').toBigInteger()
            if(currentNumberWithZeroes > previousNumber) {
                nbNumbersAdded += lengthDifference
                curentNumberToSort = currentNumberWithZeroes
            } else if (currentNumberWithNines > previousNumber) {
                val numberToAdd =
                    ((previousNumber - curentNumberToSort.toString().padEnd(previousNumber.toString().length, '0')
                        .toBigInteger()) + BigInteger.ONE).toString().padStart(lengthDifference, '0')
                nbNumbersAdded += numberToAdd.toString().length
                curentNumberToSort = (curentNumberToSort.toString() + numberToAdd.toString()).toBigInteger()
            } else {
                curentNumberToSort =
                    curentNumberToSort.toString().padEnd(previousNumber.toString().length + 1, '0').toBigInteger()
                nbNumbersAdded += lengthDifference + 1
            }

            mutableInput[i] = curentNumberToSort
        }

    }

    return nbNumbersAdded
}