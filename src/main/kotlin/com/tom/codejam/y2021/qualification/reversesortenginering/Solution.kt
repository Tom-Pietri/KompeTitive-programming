package com.tom.codejam.y2021.qualification.reversesortenginering

import java.lang.Integer.max

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val input = readLine()!!.split(" ")
            .let { InputCase(it[0].toInt(), it[1].toInt()) }
        val solution = computeSolution(input)?.map { it.toString() }?.joinToString(" ") ?: "IMPOSSIBLE"
        println("Case #${i + 1}: $solution")
    }
}

fun computeSolution(input: InputCase): List<Int?>? {

    if (input.desiredCostTooSmallForSizeOfList() || input.desiredCostTooBigForSizeOfList()) {
        return null
    }

    val costPerStep = buildCostToFindPerStep(input)

    return solveInputWithCostPerStep(input, costPerStep)
}

private fun solveInputWithCostPerStep(input: InputCase, costPerStep: List<Int>): List<Int?> {
    val reverseMidlePart = { output: List<Int?>, startOfReverseIndex: Int, nbItemsToReverse: Int ->
        val partBeforeReverse = output.subList(0, startOfReverseIndex)
        val reversedPart = output.subList(startOfReverseIndex, startOfReverseIndex + nbItemsToReverse).reversed()
        val partAfterReverse = output.subList(startOfReverseIndex + nbItemsToReverse, output.size)
        partBeforeReverse + reversedPart + partAfterReverse
    }

    var output = MutableList<Int?>(input.sizeOfList) { null }
    output[input.sizeOfList - 1] = input.sizeOfList

    costPerStep.forEachIndexed { index, nbItemsToReverse ->
        val indexOfItem = output.size - index - 2
        output[indexOfItem] = input.sizeOfList - index - 1
        output = reverseMidlePart(output, indexOfItem, nbItemsToReverse).toMutableList()
    }

    return output
}

private fun buildCostToFindPerStep(input: InputCase): List<Int> {
    val nbSteps = input.sizeOfList - 1

    val costToFindPerStep = (0 until nbSteps).map { 1 }.toMutableList()

    var total = nbSteps
    for (i in 0 until nbSteps) {
        val maxPossibleCost = nbSteps - i + 1
        costToFindPerStep[i] = when {
            total + maxPossibleCost > input.desiredCost -> max(1, input.desiredCost - total + 1)
            else -> maxPossibleCost
        }

        total = costToFindPerStep.sum()
    }
    return costToFindPerStep.reversed()
}

data class InputCase(val sizeOfList: Int, val desiredCost: Int) {
    fun desiredCostTooSmallForSizeOfList() = desiredCost < sizeOfList - 1
    fun desiredCostTooBigForSizeOfList() = (sizeOfList * (sizeOfList + 1) / 2) - 1 < desiredCost
}