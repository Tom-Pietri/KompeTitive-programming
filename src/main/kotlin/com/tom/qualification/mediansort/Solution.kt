package com.tom.qualification.mediansort

fun main() {
    val (nbOfCase, nbOfElementsToSort, nbAllowedQuestions) = readLine()!!.toList().map { it.toInt() }
    val testSetDefinition = TestSetDefinition(nbOfCase, nbOfElementsToSort, nbAllowedQuestions)
    for (i in 0 until testSetDefinition.nbOfCases) {
        println(sortNextList(testSetDefinition, JudgeSystemReal()))
        readLine()
    }
}

fun sortNextList(testSetDefinition: TestSetDefinition, judgeSystem: JudgeSystem): List<Int> {
    val originalList = MutableList(testSetDefinition.nbOfElementsToSort) { it + 1 }

    return sortList(originalList, null, null, judgeSystem)
}

private fun sortList(list: List<Int>, before: Int?, after: Int?, judgeSystem: JudgeSystem) : List<Int> {
    if(list.size <= 1) {
        return list
    }

    val originalList = list.toMutableList()

    val firstNumber : Int
    val sescondNumber : Int
    val thirdNumber : Int

    if(before != null) {
        firstNumber = before
        sescondNumber = originalList.removeFirst()
        thirdNumber = originalList.removeFirst()
    } else if (after != null) {
        firstNumber = originalList.removeFirst()
        sescondNumber = originalList.removeFirst()
        thirdNumber = after
    } else  {
        firstNumber = originalList.removeFirst()
        sescondNumber = originalList.removeFirst()
        thirdNumber = originalList.removeFirst()
    }

    val medianNumber = judgeSystem.askForMedian(firstNumber, sescondNumber, thirdNumber)

    val smallerNumbers = mutableListOf<Int>()
    val inBeetweenNumbers = mutableListOf<Int>()
    val biggerNumbers = mutableListOf<Int>()

    val smallNumber: Int
    val bigNumber: Int
    when (medianNumber) {
        firstNumber -> {
            smallNumber = firstNumber
            bigNumber = sescondNumber
            biggerNumbers.add(thirdNumber)
        }
        sescondNumber -> {
            smallNumber = firstNumber
            bigNumber = sescondNumber
            biggerNumbers.add(thirdNumber)
        }
        thirdNumber -> {
            smallNumber = firstNumber
            bigNumber = thirdNumber
            biggerNumbers.add(sescondNumber)
        }
        else -> error("")
    }

    while (originalList.isNotEmpty()) {
        val nextNumberToSort = originalList.removeFirst()
        val medianNumber = judgeSystem.askForMedian(smallNumber, bigNumber, thirdNumber)
        val listToAdd = when(medianNumber) {
            smallNumber -> smallerNumbers
            bigNumber -> biggerNumbers
            nextNumberToSort -> inBeetweenNumbers
            else -> error("Should never happen")
        }
        listToAdd.add(nextNumberToSort)
    }

    val smallerNumbersSorted = sortList(smallerNumbers, null, smallNumber, judgeSystem)
    val inBeetweenNumbersSorted = sortList(inBeetweenNumbers, smallNumber, bigNumber, judgeSystem)
    val biggerNumbersSorted = sortList(biggerNumbers, bigNumber, null, judgeSystem)
    if(before != null) {
        return smallerNumbersSorted + inBeetweenNumbersSorted + bigNumber + biggerNumbersSorted
    } else if(after != null) {
        return smallerNumbersSorted + smallNumber + inBeetweenNumbersSorted + biggerNumbersSorted
    } else {
        return smallerNumbersSorted + smallNumber + inBeetweenNumbersSorted + bigNumber + biggerNumbersSorted
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

data class TestSetDefinition(val nbOfCases: Int, val nbOfElementsToSort: Int, val nbAllowedQuestions: Int) {
    val totalAllowedQuestions = nbOfCases * nbAllowedQuestions
}
