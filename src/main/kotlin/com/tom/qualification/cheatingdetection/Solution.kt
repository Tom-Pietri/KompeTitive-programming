package com.tom.qualification.cheatingdetection

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
    return input.playerAnswers.maxByOrNull { it.nbGoodAnswers }!!.id
}


data class InputCase(val playerAnswers: List<PlayerAnswer>)

data class PlayerAnswer(val id: Int, val answer: List<Short>) {
    val nbGoodAnswers = answer.sum()
}