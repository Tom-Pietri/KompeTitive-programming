package com.tom.battledev.y2021.problem3

fun main() {
    val lines = mutableListOf<String>()
    for (i in 1..20) {
        val element = readLine()!!
        lines.add(element)
    }

    for (i in 0 until lines.size - 3) {
        val l1 = lines[i]
        val l2 = lines[i + 1]
        val l3 = lines[i + 2]
        val l4 = lines[i + 3]
        if (l1.count { it == '#' } == 9
            && l2.count { it == '#' } == 9
            && l3.count { it == '#' } == 9
            && l4.count { it == '#' } == 9) {
            val indexOf1 = l1.indexOf('.')
            val indexOf2 = l2.indexOf('.')
            val indexOf3 = l3.indexOf('.')
            val indexOf4 = l4.indexOf('.')
            if (indexOf1 == indexOf2 && indexOf2 == indexOf3 && indexOf3 == indexOf4) {
                var noBlock = true
                for (j in 0..i) {
                    if (lines[j][indexOf1] == '#') {
                        noBlock = false
                    }
                }
                if (noBlock) {
                    if (lines.size == i + 4 || lines[i + 4][indexOf1] == '#') {
                        println("BOOM ${indexOf1 + 1}")
                        return
                    }
                }
            }
        }
    }

    println("NOPE")

}