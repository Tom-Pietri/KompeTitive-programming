package com.tom.qualification.cheatingdetection

import kotlin.math.exp

fun main() {
    val nbOfCase = readLine()!!.toInt()
    val percentageToFind = readLine()!!.toInt()
    for (i in 0 until nbOfCase) {
        val playerAnswers = (1..100).map {
            PlayerAnswer(
                id = it, answer = readLine()!!.toList()
                    .map(Character::getNumericValue)
                    .map(Int::toShort)
            )
        }
        println("Case #${i + 1}: ${findCheaterIn(InputCase(playerAnswers))}")
    }
}

fun findCheaterIn(input: InputCase): Int {
    val suspiciousScore = input.player.filter { it.nbGoodAnswers > 6000 }.map { player ->

        val chanceToAnswer = input.questions.map { it.chanceToAnswerCorrectly(player.calculatedSkill) }
        player.id to chanceToAnswer
            .map { it - (1 - it) }
            .sum() / player.nbGoodAnswers
    }

    suspiciousScore.sortedBy { it.second }
    return suspiciousScore.maxByOrNull { it.second }!!.first
}

data class Question(val number: Int, val calculatedDifficulty: Double) {
    fun chanceToAnswerCorrectly(skill: Double): Double {
        return 1 / (1 + exp(-(skill - calculatedDifficulty)))
    }
}

data class InputCase(val player: List<PlayerAnswer>) {
    val questions = player[0].answer.indices.map { i ->
        val nbGoodAnswerForQuestion = player.sumBy { it.answer[i].toInt() }
        val calculatedDificulty = (nbGoodAnswerForQuestion.toDouble() / 100) * 6 - 3
        Question(i, calculatedDificulty)
    }
}

data class PlayerAnswer(val id: Int, val answer: List<Short>) {
    val nbGoodAnswers = answer.sum()
    val calculatedSkill = (nbGoodAnswers.toDouble() / answer.size) * 6 - 3
}