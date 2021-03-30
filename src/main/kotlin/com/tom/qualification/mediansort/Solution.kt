package com.tom.qualification.mediansort

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

    while (nextNumbersToSort.isNotEmpty()) {
        val nextNumberToSort = nextNumbersToSort.removeFirst()
        val listToAdd = when (judgeSystem.askForMedian(smallNumber, bigNumber, nextNumberToSort)) {
            smallNumber -> smallerNumbers
            bigNumber -> biggerNumbers
            nextNumberToSort -> inBeetweenNumbers
            else -> error("This should never happens")
        }
        listToAdd.add(nextNumberToSort)
    }

    val smallerNumbersSorted = sortList(smallerNumbers, null, smallNumber, judgeSystem)
    val inBeetweenNumbersSorted = sortList(inBeetweenNumbers, smallNumber, bigNumber, judgeSystem)
    val biggerNumbersSorted = sortList(biggerNumbers, bigNumber, null, judgeSystem)
    val sortedList = smallerNumbersSorted + smallNumber + inBeetweenNumbersSorted + bigNumber + biggerNumbersSorted

    return when {
        before != null -> {
            when (judgeSystem.askForMedian(sortedList.first(), sortedList.last(), before)) {
                sortedList.first() -> sortedList
                else -> sortedList.reversed()
            }
        }
        after != null -> {
            when (judgeSystem.askForMedian(sortedList.first(), sortedList.last(), after)) {
                sortedList.first() -> sortedList.reversed()
                else -> sortedList
            }
        }
        else -> sortedList
    }
}

interface JudgeSystem {
    fun askForMedian(first: Int, second: Int, third: Int): Int
}

class JudgeSystemReal : JudgeSystem {
    override fun askForMedian(first: Int, second: Int, third: Int): Int {
        println("$first $second $third")
        return readLine()!!.toInt()
    }
}

data class TestSetDefinition(val nbOfCases: Int, val nbOfElementsToSort: Int, val nbAllowedQuestions: Int)
