package com.tom.battledev.y2021.problem4

fun main() {
    val orbitSize = readLine()!!.toInt()
    val orbit = readLine()!!

    var count = 0
    val firstPart = orbit.substring(0 until orbitSize / 2)
    val secondPart = orbit.substring(orbitSize / 2 until orbitSize)

    val firstMap = mutableMapOf<Char, Int>()
    firstPart.forEach { firstMap[it] = (firstMap[it] ?: 0) + 1 }
    val secondMap = mutableMapOf<Char, Int>()
    secondPart.forEach { secondMap[it] = (secondMap[it] ?: 0) + 1 }

    for (i in 0 until orbitSize / 2) {
        val removedFromFirst = firstPart[i]
        val removedFromSecond = secondPart[i]
        firstMap[removedFromFirst] = (firstMap[removedFromFirst] ?: 0) - 1
        firstMap[removedFromSecond] = (firstMap[removedFromSecond] ?: 0) + 1

        secondMap[removedFromSecond] = (secondMap[removedFromSecond] ?: 0) - 1
        secondMap[removedFromFirst] = (secondMap[removedFromFirst] ?: 0) + 1

        if (firstMap.all { secondMap[it.key] == it.value }) {
            count += 2
        }
    }
    println(count)
}