package com.tom.qualification.reversesort

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for(i in 0 until nbOfCase) {
        readLine()
        val input = readLine()!!.split(" ").map(String::toInt)
        println("Case #${i + 1}: ${computeSolution(input)}")
    }
}

fun computeSolution(input: List<Int>): Int {

    var currentList = input
    var cost = 0

    for(i in 0 until input.size - 1) {
        val min = currentList.subList(i, currentList.size).minOrNull()!!
        val indexOfMin = currentList.indexOf(min) + 1

        val partBeforeReverse = currentList.subList(0, i)
        val partToReverse = currentList.subList(i, indexOfMin)
        val partAfterReverse = currentList.subList(indexOfMin, currentList.size)

        currentList = partBeforeReverse + partToReverse.reversed() + partAfterReverse
        cost += partToReverse.size
    }

    return cost
}