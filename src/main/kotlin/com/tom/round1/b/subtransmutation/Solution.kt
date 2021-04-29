package com.tom.round1.b.subtransmutation

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for (i in 0 until nbOfCase) {
        val (largestMetalToCreate, spellA, spellB) = readLine()!!.split(" ").map { it.toInt() }
        val materials = readLine()!!.split(" ").map { it.toInt() }
            .mapIndexed { materialNumber, nbWanted ->
                RequierdMaterial(
                    nbWanted = nbWanted,
                    materialNumber = materialNumber + 1
                )
            }
            .filter { it.nbWanted > 0 }
        val testCase = TestCase(largestMetalToCreate, Spell(spellA, spellB), materials = materials)
        println("Case #${i + 1}: ${computeSolution(testCase) ?: "IMPOSSIBLE"}")
    }
}

fun computeSolution(input: TestCase): Int? {
    var i = input.largestMetalToCreate
    do {
        i++
        val materialsWanted = input.materials
            .associate { it.materialNumber to it.nbWanted }
            .toMutableMap()

        val materialStock = mutableMapOf<Int, Int>()
        materialStock[i] = 1

        while (materialStock.isNotEmpty() && materialsWanted.isNotEmpty()) {
            val biggestMaterialAvailable = materialStock.keys.maxOrNull()!!
            var availableBiggestMaterial = materialStock[biggestMaterialAvailable]!!
            materialStock.remove(biggestMaterialAvailable)

            if (materialsWanted[biggestMaterialAvailable] != null) {
                val nbMaterialWanted = materialsWanted[biggestMaterialAvailable]!!

                materialsWanted[biggestMaterialAvailable] = materialsWanted[biggestMaterialAvailable]!! - availableBiggestMaterial
                availableBiggestMaterial -= nbMaterialWanted
                if (materialsWanted[biggestMaterialAvailable]!! <= 0) {
                    materialsWanted.remove(biggestMaterialAvailable)
                } else {
                    break
                }
            }

            if (availableBiggestMaterial > 0) {
                val spellResult = input.spell.apply(biggestMaterialAvailable)
                spellResult.smaller?.let { small -> materialStock.merge(small, availableBiggestMaterial, Int::plus) }
                spellResult.bigger?.let { big -> materialStock.merge(big, availableBiggestMaterial, Int::plus) }
            }
        }

        if (i > 5000) {
            return null
        }
    } while (materialsWanted.isNotEmpty())

    return i
}

data class TestCase(val largestMetalToCreate: Int, val spell: Spell, val materials: List<RequierdMaterial>)

data class RequierdMaterial(val nbWanted: Int, val materialNumber: Int)

data class Spell(val spellA: Int, val spellB: Int) {
    fun apply(material: Int): SpellResult {
        return when {
            spellA > material -> SpellResult(null, null)
            spellB > material -> SpellResult(material - spellA, null)
            else -> SpellResult(material - spellA, material - spellB)
        }
    }
}

data class SpellResult(val smaller: Int?, val bigger: Int?)
