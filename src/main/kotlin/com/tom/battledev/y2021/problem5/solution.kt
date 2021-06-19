package com.tom.battledev.y2021.problem5

fun main() {
//    Ligne 1 : trois entiers N, A, C: respectivement le nombre de secondes avant de sortir du champ d'astéroïdes, la durée en secondes d'une activation du bouclier, et la durée minimale en secondes du refroidissement du bouclier
//
//    Ligne 2 : N entiers, qui indiquent pour chaque seconde du parcours le nombre d'astéroïdes rencontrés.

    val (nbSec, shieldDuration, shieldCooldown) = readLine()!!.split(" ").map { it.toInt() }
    val seconds = readLine()!!.split(" ").map { it.toInt() }

    var i = 0
    while (i < nbSec) {
        val maxToTry = i + shieldCooldown



        i++
    }
}