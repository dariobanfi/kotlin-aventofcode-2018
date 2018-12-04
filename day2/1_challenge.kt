package day2

import java.io.File

fun main(args : Array<String>) {
    val fileLines: List<String> = File("day2/input.txt").readLines()

    var twos = 0
    var threes = 0

    fileLines.forEach { line ->
        val charactersMap = mutableMapOf<Char, Int>()
        line.forEach { char ->
            charactersMap[char] = charactersMap.getOrDefault(char, 0) + 1
        }
        twos += charactersMap.values.distinct().count { it == 2 }
        threes += charactersMap.values.distinct().count { it == 3 }
    }

    println("Twos: $twos")
    println("Threes: $threes")
    println("Checksum: ${twos * threes}")
}


