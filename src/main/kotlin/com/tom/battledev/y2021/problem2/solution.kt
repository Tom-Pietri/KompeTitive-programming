package com.tom.battledev.y2021.problem2

fun main() {
    val nbButons = readLine()!!.toInt()
    val buttons = mutableMapOf<String, Int>()
    repeat(nbButons) {
        val button = readLine()!!
        if (buttons.containsKey(button)) {
            buttons[button] = buttons[button]!! + 1
        } else {
            buttons[button] = 1
        }
    }

    val filter = buttons.filter { it.value == 2 }
    println(filter.entries.first().key)
}
