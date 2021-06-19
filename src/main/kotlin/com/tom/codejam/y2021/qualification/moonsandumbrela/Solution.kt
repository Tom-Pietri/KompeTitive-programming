package com.tom.codejam.y2021.qualification.moonsandumbrela

fun main() {
    val nbOfCase = readLine()!!.toInt()

    for(i in 0 until nbOfCase) {
        val input = readLine()!!.split(" ")
            .let { InputCase(it[0].toInt(), it[1].toInt(), it[2]) }
        println("Case #${i + 1}: ${computeSolution(input)}")
    }
}

fun computeSolution(input: InputCase) : Int {
    val muralWithoutInterogation = if (input.priceOfCJ + input.priceOfJC < 0) {
        transformNegativeInput(input)
    } else {
        input.muralAsString.replace(Regex("\\?"), "")
    }

    var price = 0
    for(i in 0 until muralWithoutInterogation.length - 1) {
        val currentChar = muralWithoutInterogation[i]
        val nextChar = muralWithoutInterogation[i + 1]
        price += when {
            currentChar == 'C' && nextChar == 'J' -> input.priceOfCJ
            currentChar == 'J' && nextChar == 'C' -> input.priceOfJC
            else -> 0
        }
    }

    return price
}

private fun transformNegativeInput(input: InputCase): String {
    val cheapPair = if (input.priceOfJC < 0) {
        "JC"
    } else {
        "CJ"
    }

    var i = 0
    var transformedMural = input.muralAsString
    while(i < transformedMural.length) {
        if(transformedMural[i] != '?') {
            i++
        } else {
            val start = i
            while (i < transformedMural.length && transformedMural[i] == '?') {
                i++
            }

            var interogationMarkGroup = transformedMural.substring(start, i)
            val charBeforeGroup = transformedMural.getOrNull(start - 1)
            val charAfterGroup = transformedMural.getOrNull(i)
            val twoInterrogationMarkRegex = Regex("\\?{2}")
            interogationMarkGroup = if (interogationMarkGroup.length % 2 == 0) {
                interogationMarkGroup.replace(twoInterrogationMarkRegex, cheapPair)
            } else {
                if(charBeforeGroup == cheapPair[0]) {
                    cheapPair[1] + interogationMarkGroup.drop(1).replace(twoInterrogationMarkRegex, cheapPair)
                } else if(charAfterGroup == cheapPair[1]) {
                    interogationMarkGroup.dropLast(1).replace(twoInterrogationMarkRegex, cheapPair) + cheapPair[0]
                } else {
                    interogationMarkGroup.replace(twoInterrogationMarkRegex, cheapPair)
                }
            }

            transformedMural = transformedMural.substring(0, start) + interogationMarkGroup + transformedMural.substring(i)

        }
    }

    return transformedMural.replace(Regex("\\?"), "")
}

data class InputCase(val priceOfCJ : Int, val priceOfJC : Int, val muralAsString: String)
