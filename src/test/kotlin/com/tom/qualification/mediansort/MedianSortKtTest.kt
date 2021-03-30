package com.tom.qualification.mediansort

import org.junit.jupiter.api.Test
import strikt.api.DescribeableBuilder
import strikt.api.expectThat
import strikt.assertions.isContainedIn
import strikt.assertions.isLessThan

internal class MedianSortKtTest {

    @Test
    fun `Test example 1`() {
        val testSetDefinition = TestSetDefinition(1, 5, 10000)
        val expectedList = listOf(5, 4, 3, 2, 1)

        val judgeSystem = JudgeSystemFake(expectedList)
        val sortedList = sortNextList(testSetDefinition, judgeSystem)

        expectThat(sortedList) {
            get { this }.isEqualOrReverse(expectedList)
        }
    }

    @Test
    fun `Test example 2`() {
        val testSetDefinition = TestSetDefinition(1, 5, 10000)
        val expectedList = listOf(1, 3, 5, 4, 2)

        val sortedList = sortNextList(testSetDefinition ,JudgeSystemFake(expectedList))

        expectThat(sortedList).isEqualOrReverse(expectedList)
    }

   @Test
        fun `Test like test set 1`() {
        val nbOfCase = 1000
        val nbNumbersToSort = 10
        val nbAllowedQuestionPerNumber = 300
        val testSetDefinition = TestSetDefinition(nbOfCase, nbNumbersToSort, nbAllowedQuestionPerNumber)

        var totalQuestionAsked = 0
        for(i in 0 until nbOfCase) {
            val expectedList = List(nbNumbersToSort) { it + 1}.shuffled()
            val judgeSystemFake = JudgeSystemFake(expectedList)
            val sortedList = sortNextList(testSetDefinition , judgeSystemFake)
            expectThat(sortedList).isEqualOrReverse(expectedList)
            totalQuestionAsked += judgeSystemFake.nbTimesAsked
        }

        expectThat(totalQuestionAsked).isLessThan(nbOfCase * nbAllowedQuestionPerNumber)
    }

    @Test
    fun `Test like test set 2`() {
        val nbOfCase = 1000
        val nbNumbersToSort = 50
        val nbAllowedQuestionPerNumber = 300
        val testSetDefinition = TestSetDefinition(nbOfCase, nbNumbersToSort, nbAllowedQuestionPerNumber)

        var totalQuestionAsked = 0
        for(i in 0 until nbOfCase) {
            val expectedList = List(nbNumbersToSort) { it + 1}.shuffled()
            val judgeSystemFake = JudgeSystemFake(expectedList)
            val sortedList = sortNextList(testSetDefinition , judgeSystemFake)
            expectThat(sortedList).isEqualOrReverse(expectedList)
            totalQuestionAsked += judgeSystemFake.nbTimesAsked
        }

        expectThat(totalQuestionAsked).isLessThan(nbOfCase * nbAllowedQuestionPerNumber)
    }

    @Test
    fun `Test like test set 3`() {
        val nbOfCase = 1000
        val nbNumbersToSort = 50
        val nbAllowedQuestionPerNumber = 170
        val testSetDefinition = TestSetDefinition(nbOfCase, nbNumbersToSort, nbAllowedQuestionPerNumber)

        var totalQuestionAsked = 0
        for(i in 0 until nbOfCase) {
            val expectedList = List(nbNumbersToSort) { it + 1}.shuffled()
            val judgeSystemFake = JudgeSystemFake(expectedList)
            val sortedList = sortNextList(testSetDefinition , judgeSystemFake)
            expectThat(sortedList).isEqualOrReverse(expectedList)
            totalQuestionAsked += judgeSystemFake.nbTimesAsked
        }

        expectThat(totalQuestionAsked).isLessThan(nbOfCase * nbAllowedQuestionPerNumber)
    }


    @Test
    fun `Test like test set 3 with list in order`() {
        val nbOfCase = 1000
        val nbNumbersToSort = 50
        val nbAllowedQuestionPerNumber = 170
        val testSetDefinition = TestSetDefinition(nbOfCase, nbNumbersToSort, nbAllowedQuestionPerNumber)

        var totalQuestionAsked = 0
        for(i in 0 until nbOfCase) {
            val expectedList = List(nbNumbersToSort) { it + 1}
            val judgeSystemFake = JudgeSystemFake(expectedList)
            val sortedList = sortNextList(testSetDefinition , judgeSystemFake)
            expectThat(sortedList).isEqualOrReverse(expectedList)
            totalQuestionAsked += judgeSystemFake.nbTimesAsked
        }

        expectThat(totalQuestionAsked).isLessThan(nbOfCase * nbAllowedQuestionPerNumber)
    }

}

fun DescribeableBuilder<List<Int>>.isEqualOrReverse(expected: List<Int>) {
    this.isContainedIn(listOf(expected, expected.reversed()))
}

class JudgeSystemFake(private val list: List<Int>) : JudgeSystem {

    var nbTimesAsked = 0

    override fun askForMedian(first: Int, second: Int, third: Int): Median {
        nbTimesAsked++
        val firstIndex = list.indexOf(first)
        val secondIndex = list.indexOf(second)
        val thirdIndex = list.indexOf(third)

        return if(secondIndex in (firstIndex + 1) until thirdIndex || secondIndex in (thirdIndex + 1) until firstIndex) {
            Median.SECOND
        } else if(thirdIndex in (firstIndex + 1) until secondIndex || thirdIndex in (secondIndex + 1) until firstIndex) {
            Median.THIRD
        } else {
            Median.FIRST
        }
    }

}