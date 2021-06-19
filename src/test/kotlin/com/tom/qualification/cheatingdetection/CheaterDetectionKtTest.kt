package com.tom.qualification.cheatingdetection

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThan
import strikt.assertions.isGreaterThanOrEqualTo
import java.io.File

internal class CheaterDetectionKtTest {
    @Test
    fun `Sample test case should find cheater 59`() {
        val sampleFile = File("./src/main/resources/sampleCheater.txt").bufferedReader()

        sampleFile.readLine()
        sampleFile.readLine()
        val playerAnswers = (1..100).map {
            PlayerAnswer(id = it, answer = sampleFile.readLine()!!.toList()
                .map{ Character.getNumericValue(it).toShort()})
        }

        val cheaterId = findCheaterIn(InputCase(playerAnswers))

        expectThat(cheaterId).isEqualTo(59)
    }

    @Test
    fun `Run first test case`() {
        val testFileInput = File("./src/main/resources/ts1_input.txt").bufferedReader()
        val testFileOutput = File("./src/main/resources/ts1_output.txt").bufferedReader()

        val nbTestCase = testFileInput.readLine().toInt()
        testFileInput.readLine()
        var nbCorrectMatch = 0
        repeat(nbTestCase) {
            val playerAnswers = (1..100).map {
                PlayerAnswer(id = it, answer = testFileInput.readLine()!!.toList()
                    .map{ Character.getNumericValue(it).toShort()})
            }

//            Case #1: 54
            val expectedId = testFileOutput.readLine().split(" ")[2].toInt()
            val cheaterId = findCheaterIn(InputCase(playerAnswers))
            if (expectedId == cheaterId) {
                nbCorrectMatch++
            }
        }

        expectThat(nbCorrectMatch).isGreaterThanOrEqualTo(10)
    }

    @Test
    fun `Run second test case`() {
        val testFileInput = File("./src/main/resources/ts2_input.txt").bufferedReader()
        val testFileOutput = File("./src/main/resources/ts2_output.txt").bufferedReader()

        val nbTestCase = testFileInput.readLine().toInt()
        testFileInput.readLine()
        var nbCorrectMatch = 0
        repeat(nbTestCase) {
            val playerAnswers = (1..100).map {
                PlayerAnswer(id = it, answer = testFileInput.readLine()!!.toList()
                    .map{ Character.getNumericValue(it).toShort()})
            }

//            Case #1: 54
            val expectedId = testFileOutput.readLine().split(" ")[2].toInt()
            val cheaterId = findCheaterIn(InputCase(playerAnswers))
            if (expectedId == cheaterId) {
                nbCorrectMatch++
            }
        }

        expectThat(nbCorrectMatch).isGreaterThan(50)
    }
}