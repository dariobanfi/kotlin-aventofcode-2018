package day5

import java.io.File

fun main(args : Array<String>) {
    val input: String = File("day5/input.txt").readText()

    val atoms = input.toLowerCase().toList().distinct()

    val polymerLengths = mutableMapOf<Char, Int>()
    atoms.forEach { atom ->
        val cleanPolymer = input.replace(atom.toString(), "").replace(atom.toUpperCase().toString(), "")
        val len = processPolymer(cleanPolymer.toMutableList())
        polymerLengths[atom] = len - 1
    }

    println("Solution: ${polymerLengths.minBy { it.value }?.value}")


}

fun atomsReact(atom1: Char, atom2: Char): Boolean {
    return when {
        atom1.toLowerCase() == atom2.toLowerCase() && atom1 != atom2 -> true
        else -> false
    }
}

fun processPolymer(polymer: MutableList<Char>): Int {
    var stable = false
    var collapsed = false
    var i = 0
    while (!stable) {
        if (i + 2 > polymer.size) {
            if (!collapsed) {
                stable = true
            }
            i = 0
            collapsed = false
        } else {
            if (atomsReact(polymer[i], polymer[i + 1])) {
                polymer.removeAt(i)
                polymer.removeAt(i)
                collapsed = true
            } else {
                i++
            }
        }
    }
    return polymer.size
}
