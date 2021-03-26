package com.tom.qualification.reversesortenginering

import java.lang.Integer.max

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for(i in 0 until nbOfCase) {
        val input = readLine()!!.split(" ")
            .let { InputCase(it[0].toInt(), it[1].toInt()) }
        val solution = computeSolution(input)?.map { it.toString() }?.joinToString(" ")  ?: "IMPOSSIBLE"
        println("Case #${i + 1}: $solution")
    }
}

fun computeSolution(input: InputCase): List<Int?>? {

    val nbSteps = input.sizeOfList - 1;
    if (input.desiredCost < nbSteps) {
        return null
    } else if (((input.sizeOfList) * (input.sizeOfList + 1) / 2) - 1 < input.desiredCost) {
        return null
    }

    val nbPerSteps = (0 until nbSteps).map { 1 }.toMutableList()
    var total = nbSteps
    for (i in 0 until nbSteps) {
        if (total + nbSteps - i + 1 > input.desiredCost) {
            nbPerSteps[i] = max(1, input.desiredCost - total + 1)
        } else {
            nbPerSteps[i] = nbSteps - i + 1
        }

        total = nbPerSteps.sum()
    }

    var output = MutableList<Int?>(input.sizeOfList) { null }
    output[input.sizeOfList - 1] = input.sizeOfList

    nbPerSteps.reversed().forEachIndexed { index, nbItemsToReverse ->
        val nextItemIndex = output.size - index - 2
        output[nextItemIndex] = input.sizeOfList - index - 1
        val reversedPart = output.subList(nextItemIndex, nextItemIndex + nbItemsToReverse).reversed()
        output = (output.subList(0, nextItemIndex) + reversedPart + output.subList(nextItemIndex + nbItemsToReverse, output.size)).toMutableList()
    }

    return output
}

data class InputCase(val sizeOfList: Int, val desiredCost: Int)