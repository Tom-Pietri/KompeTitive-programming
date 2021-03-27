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
    val oneAsShort = 1.toShort()
    val suspiciousScore = input.player.filter { it.nbGoodAnswers > 6000 }.map { player ->
        val chancesRangeToCorrectAnswers = ChancesToAnserCorrectlyRange.values().map { it to 0 }.toMap().toMutableMap()
        val chancesRangeToWrongAnswers = ChancesToAnserCorrectlyRange.values().map { it to 0 }.toMap().toMutableMap()

        player.answer.forEachIndexed { i, answer ->
            val chanceToAnswerCorrectly = input.questions[i].chanceToAnswerCorrectly(player.calculatedSkill) * 100

            val forChances = ChancesToAnserCorrectlyRange.forChances(chanceToAnswerCorrectly)
            if (answer == oneAsShort) {
                chancesRangeToCorrectAnswers[forChances] = chancesRangeToCorrectAnswers[forChances]!! + 1
            }
         }

        player.id to chancesRangeToCorrectAnswers.map { it.key.suspiciousWeight * it.value }.sum()
    }

    suspiciousScore.find { it.first == 59 }
    val sorted = suspiciousScore.sortedBy { it.second }
    return suspiciousScore.maxByOrNull { it.second }!!.first

//    return input.player.maxByOrNull { it.nbGoodAnswers }!!.id
}

data class Answers(val correct: Int = 0, val wrong: Int = 0)

private enum class ChancesToAnserCorrectlyRange(val suspiciousWeight: Int) {
    LESS_THAN_ONE(25),
    ONE_TO_TWO(20),
    TWO_TO_FIVE(15),
    FIVE_TO_TEN(10),
    TEN_TO_TWENTY(5),
    TWENTY_TO_THIRTY(3),
    THIRTY_TO_FOURTY(2),
    FORTY_TO_FIFTY(2),
    FIFTY_TO_HUNDRED(2);

    companion object {
        fun forChances(chanceToAnswerCorrectly: Double): ChancesToAnserCorrectlyRange {
            return when {
                chanceToAnswerCorrectly < 1 -> LESS_THAN_ONE
                chanceToAnswerCorrectly < 2 -> ONE_TO_TWO
                chanceToAnswerCorrectly < 5 -> TWO_TO_FIVE
                chanceToAnswerCorrectly < 10 -> FIVE_TO_TEN
                chanceToAnswerCorrectly < 20 -> TEN_TO_TWENTY
                chanceToAnswerCorrectly < 30 -> TWENTY_TO_THIRTY
                chanceToAnswerCorrectly < 40 -> THIRTY_TO_FOURTY
                chanceToAnswerCorrectly < 50 -> FORTY_TO_FIFTY
                else -> FIFTY_TO_HUNDRED
            }
        }
    }

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