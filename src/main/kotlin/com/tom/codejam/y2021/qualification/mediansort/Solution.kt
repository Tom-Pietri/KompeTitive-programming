package com.tom.codejam.y2021.qualification.mediansort

fun main() {
    val (nbOfCase, nbOfElementsToSort, nbAllowedQuestions) = readLine()!!.split(" ").map { it.toInt() }
    val testSetDefinition = TestSetDefinition(nbOfCase, nbOfElementsToSort, nbAllowedQuestions)
    for (i in 1..testSetDefinition.nbOfCases) {
        val sortedList = sortNextList(testSetDefinition, JudgeSystemReal())
        println(sortedList.joinToString(" ") { it.toString() })
        val result = readLine()!!.toInt()
        if (result == -1) {
            return
        }
    }
}

fun sortNextList(testSetDefinition: TestSetDefinition, judgeSystem: JudgeSystem): List<Int> {
    val originalList = List(testSetDefinition.nbOfElementsToSort) { it + 1 }.shuffled()

    return sortList(originalList, null, null, judgeSystem)
}

private fun sortList(list: List<Int>, before: Int?, after: Int?, judgeSystem: JudgeSystem): List<Int> {
    if (list.size <= 1) {
        return list
    }

    val nextNumbersToSort = list.toMutableList()

    val smallNumber: Int = nextNumbersToSort.removeFirst()
    val bigNumber: Int = nextNumbersToSort.removeFirst()

    val smallerNumbers = mutableListOf<Int>()
    val inBeetweenNumbers = mutableListOf<Int>()
    val biggerNumbers = mutableListOf<Int>()

    val sortNextNumber: (Int) -> Boolean = { numberToSort : Int ->
        val listToAdd = when (judgeSystem.askForMedian(smallNumber, bigNumber, numberToSort)) {
            Median.FIRST -> smallerNumbers
            Median.SECOND -> biggerNumbers
            Median.THIRD -> inBeetweenNumbers
        }
        listToAdd.add(numberToSort)
    }

    while (nextNumbersToSort.isNotEmpty()) {
        sortNextNumber(nextNumbersToSort.removeFirst())
    }

    val smallerNumbersSorted = sortList(smallerNumbers, null, smallNumber, judgeSystem)
    val inBeetweenNumbersSorted = sortList(inBeetweenNumbers, smallNumber, bigNumber, judgeSystem)
    val biggerNumbersSorted = sortList(biggerNumbers, bigNumber, null, judgeSystem)
    val sortedList = smallerNumbersSorted + smallNumber + inBeetweenNumbersSorted + bigNumber + biggerNumbersSorted

    return when {
        before != null -> {
            when (judgeSystem.askForMedian(sortedList.first(), sortedList.last(), before)) {
                Median.FIRST -> sortedList
                else -> sortedList.reversed()
            }
        }
        after != null -> {
            when (judgeSystem.askForMedian(sortedList.first(), sortedList.last(), after)) {
                Median.FIRST -> sortedList.reversed()
                else -> sortedList
            }
        }
        else -> sortedList
    }
}

interface JudgeSystem {
    fun askForMedian(first: Int, second: Int, third: Int): Median
}

enum class Median {
    FIRST, SECOND, THIRD
}

class JudgeSystemReal : JudgeSystem {
    override fun askForMedian(first: Int, second: Int, third: Int): Median {
        println("$first $second $third")
        return when(readLine()!!.toInt()) {
            first -> Median.FIRST
            second -> Median.SECOND
            else -> Median.THIRD
        }
    }
}

data class TestSetDefinition(val nbOfCases: Int, val nbOfElementsToSort: Int, val nbAllowedQuestions: Int)
