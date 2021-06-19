package com.tom.round1.b.digitblocks

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isTrue
import java.math.BigInteger
import kotlin.random.Random

internal class DigitBlocksKtTest {

    @Test
    fun testSolution() {
        var total = BigInteger.ZERO
        repeat(50) {
            val judgeSystemMock = JudgeSystemMock()
            computeSolution(judgeSystemMock)
            total += judgeSystemMock.totalScore()
        }
        expectThat(total > BigInteger("937467793908762347")).isTrue()
    }
}

class JudgeSystemMock : JudgeSystem {

    private val towers = List(20) { Tower(it, mutableListOf(), 15) }
    private var currentBlock = 0

    fun totalScore(): BigInteger {
        return towers.map { it.blocks.reversed() }
            .map { it.joinToString("") }
            .map { BigInteger(it) }
            .reduce(BigInteger::add)
    }

    override fun placeBlock(position: Int) {
        towers[position - 1].blocks.add(currentBlock)
    }

    override fun getNextBlock(): Int {
        val nextInt = Random.nextInt(0, 10)
        currentBlock = nextInt
        return nextInt
    }
}

