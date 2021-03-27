package com.tom.qualification.mediansort

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MedianSortKtTest {

    @Test
    fun testCase1() {
        val testSetDefinition = TestSetDefinition(1, 5, 10000)
        val expectedList = listOf(5, 4, 3, 2, 1)

        val sortedList = sortNextList(testSetDefinition ,JudgeSystemFake(expectedList))

        expectThat(sortedList).isEqualTo(expectedList.reversed())
    }

    @Test
    fun testCase2() {
        val testSetDefinition = TestSetDefinition(1, 5, 10000)
        val expectedList = listOf(1, 3, 5, 4, 2)

        val sortedList = sortNextList(testSetDefinition ,JudgeSystemFake(expectedList))

        expectThat(sortedList).isEqualTo(expectedList)
    }

}

class JudgeSystemFake(private val list: List<Int>) : JudgeSystem {

    override fun askForMedian(first: Int, second: Int, third: Int): Int {

        val firstIndex = list.indexOf(first)
        val secondIndex = list.indexOf(second)
        val thirdIndex = list.indexOf(third)

        return if(secondIndex in (firstIndex + 1) until thirdIndex || secondIndex in (thirdIndex + 1) until firstIndex) {
            second
        } else if(thirdIndex in (firstIndex + 1) until secondIndex || thirdIndex in (second + 1) until first) {
            third
        } else {
            first
        }
    }

}