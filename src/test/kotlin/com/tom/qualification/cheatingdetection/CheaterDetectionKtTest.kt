package com.tom.qualification.cheatingdetection

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
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

        playerAnswers.find { it.id == 59 }!!.let { println(it.nbGoodAnswers) }
        playerAnswers.maxByOrNull { it.nbGoodAnswers }!!.let {
            println(it.id)
            println(it.nbGoodAnswers)
        }
        expectThat(cheaterId).isEqualTo(59)
    }
}