package com.tom.round1.b.digitblocks

fun main() {
    readLine()
//    val nbOfCase = readLine()!!.split(" ").map { it.toInt() }[0]
    val nbOfCase = 50
    for (i in 0 until nbOfCase) {
        computeSolution(JudgeSystemReal())
    }
}

fun computeSolution(judgeSystem: JudgeSystem) {
    val nbTowerToBuild = 20
    val towerSize = 15
    val towers = List(20) { Tower(it, mutableListOf(), towerSize) }
    var notFullTowers = towers.toMutableList()

    var nbPlacedBlocks = 0
    while (nbPlacedBlocks < nbTowerToBuild * towerSize) {
        val nextBlock = judgeSystem.getNextBlock()
        val towerToAdd = when (nextBlock) {
            9 -> notFullTowers.find { it.isMissingOneBlock() }
                ?: notFullTowers.find { it.isMissingTwoBlock() }
                ?: notFullTowers.first()
            8 -> notFullTowers.find { it.isMissingTwoBlock() }
                ?: notFullTowers.filter { !it.isMissingOneBlock() }.firstOrNull()
                ?: notFullTowers.first()
            else -> {
                notFullTowers.filter { !it.isMissingOneBlock() }.filter { !it.isMissingTwoBlock() }.firstOrNull()
                    ?: notFullTowers.find { it.isMissingTwoBlock() }
                    ?: notFullTowers.find { it.isMissingOneBlock() }
                    ?: notFullTowers.first()
            }
        }
        towerToAdd.blocks.add(nextBlock)


        judgeSystem.placeBlock(towerToAdd.idx + 1)
        notFullTowers = notFullTowers.filter { !it.isFull() }.toMutableList()
        nbPlacedBlocks++
    }
}

data class Tower(val idx: Int, val blocks: MutableList<Int>, val towerSize: Int) {
    fun isFull() = blocks.size >= towerSize
    fun isMissingOneBlock() = blocks.size == towerSize - 1
    fun isMissingTwoBlock() = blocks.size == towerSize - 2
}

interface JudgeSystem {
    fun placeBlock(position: Int)
    fun getNextBlock(): Int
}

class JudgeSystemReal : JudgeSystem {
    override fun placeBlock(position: Int) {
        println("$position")
    }

    override fun getNextBlock(): Int {
        return readLine()!!.toInt()
    }
}

