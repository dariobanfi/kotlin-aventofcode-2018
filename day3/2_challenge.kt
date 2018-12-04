package day3

import java.io.File

fun main(args : Array<String>) {
    val fileLines: List<String> = File("day3/input.txt").readLines()

    val matrix = Array(1000, {IntArray(1000)})

    val conflicting = mutableSetOf<Int>()
    val identifiers = mutableSetOf<Int>()

    fileLines.forEach { line ->
        val splittedLine = line.split(" ")
        val identifier = splittedLine[0].replace("#", "").toInt()
        val x = splittedLine[2].split(",")[0].toInt()
        val y = splittedLine[2].split(",")[1].replace(":", "").toInt()
        val lengthX = splittedLine[3].split("x")[0].toInt()
        val lengthY = splittedLine[3].split("x")[1].toInt()

        identifiers.add(identifier)

        (x..(x + lengthX - 1)).forEach { i ->
            (y..(y + lengthY - 1)).forEach { j ->
                if (matrix[i][j] == 0) {
                    matrix[i][j] = identifier
                } else {
                    conflicting.add(matrix[i][j])
                    conflicting.add(identifier)
                }
            }
        }
    }

    val difference = identifiers.subtract(conflicting)

    println("Solution: ${difference.first()}")
}
