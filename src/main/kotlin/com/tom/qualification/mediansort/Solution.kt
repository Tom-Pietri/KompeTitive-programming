package com.tom.qualification.mediansort

fun main() {
    val (nbOfCase, nbOfElementsToSort, nbAllowedQuestions) = readLine()!!.split(" ").map { it.toInt() }
    val testSetDefinition = TestSetDefinition(nbOfCase, nbOfElementsToSort, nbAllowedQuestions)
    for (i in 1..testSetDefinition.nbOfCases) {
        val sortedList = sortNextList(testSetDefinition, JudgeSystemReal())
        if (sortedList.isEmpty()) {
            return
        }
        println(sortedList.joinToString(" ") { it.toString() })
        val result = readLine()!!.toInt()
        if (result == -1) {
            return
        }
    }
}

fun sortNextList(testSetDefinition: TestSetDefinition, judgeSystem: JudgeSystem): List<Int> {
    val originalList = MutableList(testSetDefinition.nbOfElementsToSort) { it + 1 }

    return sortList(originalList, null, null, judgeSystem)
}

private fun sortList(list: List<Int>, before: Int?, after: Int?, judgeSystem: JudgeSystem): List<Int> {
    if (list.size <= 1) {
        return list
    }

    val originalList = list.toMutableList()

    val firstNumber: Int
    val secondNumber: Int
    val thirdNumber: Int

    when {
        before != null -> {
            firstNumber = before
            secondNumber = originalList.removeFirst()
            thirdNumber = originalList.removeFirst()
        }
        after != null -> {
            firstNumber = originalList.removeFirst()
            secondNumber = originalList.removeFirst()
            thirdNumber = after
        }
        else -> {
            firstNumber = originalList.removeFirst()
            secondNumber = originalList.removeFirst()
            thirdNumber = originalList.removeFirst()
        }
    }

    val smallerNumbers = mutableListOf<Int>()
    val inBeetweenNumbers = mutableListOf<Int>()
    val biggerNumbers = mutableListOf<Int>()

    val smallNumber: Int
    val bigNumber: Int
    when (judgeSystem.askForMedian(firstNumber, secondNumber, thirdNumber)) {
        firstNumber -> {
            smallerNumbers.add(secondNumber)
            smallNumber = firstNumber
            bigNumber = thirdNumber
        }
        secondNumber -> {
            smallNumber = firstNumber
            inBeetweenNumbers.add(secondNumber)
            bigNumber = thirdNumber
        }
        thirdNumber -> {
            smallNumber = firstNumber
            bigNumber = thirdNumber
            biggerNumbers.add(secondNumber)
        }
        -1 -> return emptyList()
        else -> error("")
    }

    while (originalList.isNotEmpty()) {
        val nextNumberToSort = originalList.removeFirst()
        val listToAdd = when (judgeSystem.askForMedian(smallNumber, bigNumber, nextNumberToSort)) {
            smallNumber -> smallerNumbers
            bigNumber -> biggerNumbers
            nextNumberToSort -> inBeetweenNumbers
            else -> return emptyList()
        }
        listToAdd.add(nextNumberToSort)
    }

    val smallerNumbersSorted = sortList(smallerNumbers, null, smallNumber, judgeSystem)
    val inBeetweenNumbersSorted = sortList(inBeetweenNumbers, smallNumber, bigNumber, judgeSystem)
    val biggerNumbersSorted = sortList(biggerNumbers, bigNumber, null, judgeSystem)
    return when {
        before != null -> smallerNumbersSorted + inBeetweenNumbersSorted + bigNumber + biggerNumbersSorted
        after != null -> smallerNumbersSorted + smallNumber + inBeetweenNumbersSorted + biggerNumbersSorted
        else -> smallerNumbersSorted + smallNumber + inBeetweenNumbersSorted + bigNumber + biggerNumbersSorted
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
