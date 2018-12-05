package day5

import java.io.File

fun main(args : Array<String>) {
    val input = File("day5/input.txt").readText().toMutableList()

    var stable = false
    var collapsed = false
    var i = 0
    while (!stable) {
        if (i + 2 > input.size) {
            if (!collapsed) {
                stable = true
            }
            i = 0
            collapsed = false
        } else {
            if (doAtomsReact(input[i], input[i + 1])) {
                input.removeAt(i)
                input.removeAt(i)
                collapsed = true
            } else {
                i++
            }
        }
    }

    println("Solution ${input.size}")
}

fun doAtomsReact(atom1: Char, atom2: Char): Boolean {
    return when {
        atom1.toLowerCase() == atom2.toLowerCase() && atom1 != atom2 -> true
        else -> false
    }
}
