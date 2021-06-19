package com.tom.codejam.y2020.robotprogrammingstrategy

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val nbPlayers = readLine()!!.toInt()
        val input = (0 until nbPlayers).map { readLine()!! }
        println("Case #${i + 1}: ${computeSolution(input)}")
    }
}

fun computeSolution(input: List<String>): String {
    val opponentsToBeatPrograms = input.toMutableList()
    val winningProgram = StringBuilder()

    var i = 0
    while (opponentsToBeatPrograms.isNotEmpty()) {
        val movesToBeat = opponentsToBeatPrograms.map { it[i % it.length] }
        val distinctMovesToBeat = movesToBeat.distinct()
        when (distinctMovesToBeat.size) {
            3 -> return "IMPOSSIBLE"
            2 -> winningProgram.append(chooseWinningMove(distinctMovesToBeat, opponentsToBeatPrograms, i))
            else -> {
                winningProgram.append(movesToBeat.first().moveBeatingMe())
                opponentsToBeatPrograms.removeAll { true }
            }
        }

        i++
    }

    return winningProgram.toString()
}

private fun chooseWinningMove(
    distinctMovesToBeat: List<Char>,
    opponentsToBeat: MutableList<String>,
    currentProgramPointer: Int
): Char {
    return if (distinctMovesToBeat[1] == distinctMovesToBeat.first().moveBeatingMe()) {
        opponentsToBeat.removeAll { it[currentProgramPointer % it.length] == distinctMovesToBeat.first() }
        distinctMovesToBeat.first().moveBeatingMe()
    } else {
        opponentsToBeat.removeAll { it[currentProgramPointer % it.length] == distinctMovesToBeat[1] }
        distinctMovesToBeat[1].moveBeatingMe()
    }
}

fun Char.moveBeatingMe(): Char =
    when (this) {
        'R' -> 'P'
        'P' -> 'S'
        else -> 'R'
    }