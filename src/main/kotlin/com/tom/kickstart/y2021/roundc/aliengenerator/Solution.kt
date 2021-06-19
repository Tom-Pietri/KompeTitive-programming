package com.tom.kickstart.y2021.roundc.aliengenerator

import java.math.BigInteger

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for(i in 0 until nbOfCase) {
        val toInt = readLine()!!.toBigInteger()
        println("Case #${i + 1}: ${computeSolution(toInt)}")
    }
}

fun computeSolution(G: BigInteger): Int {
    var total = 0

    val init = findProducedWith(BigInteger.ONE, G)

    var i = BigInteger.ONE
    while (i <= G.divide(BigInteger.TWO)) {
        if (init.produced == G) {
            println("$i - ${init.days}")
            total++
        }

        val potentialNextProduced = init.produced - i
        if(potentialNextProduced < G) {
            init.produced += init.days
        } else if(potentialNextProduced >= G) {
            init.produced = potentialNextProduced
            init.days--
        }

        i++
    }

    return total + 1
}

fun findProducedWith(i: BigInteger, G: BigInteger): Produced {
    var produced = BigInteger.ZERO
    var days = BigInteger.ZERO
    while (produced < G) {
        produced += (i + days)
        days++
    }

    return Produced(produced, days)
}

data class Produced(var produced: BigInteger, var days: BigInteger)