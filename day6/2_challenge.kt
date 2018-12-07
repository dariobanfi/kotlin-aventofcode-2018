package day6

import java.io.File
import kotlin.math.abs

fun main(args : Array<String>) {
    val input = File("day6/input.txt").readLines()

    val coords = input.map {
        val (x, y) = it.split(", ")
        Pair(x.toInt(), y.toInt())
    }

    val highestX: Int = coords.map { it.first }.max()!!
    val highestY: Int = coords.map { it.second }.max()!!


    val matrix = Array(highestX + 1, { Array(highestY + 1, { "." })})
    coords.forEachIndexed { index, line ->
         matrix[line.first][line.second] = index.toString()
    }

    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[i].size) {
            matrix[i][j] = isDesiredRegion(coords, i, j)
            print(matrix[i][j])
        }
        println("")
    }

    val solution = matrix
        .flatten()
        .filter { it == "#" }
        .count()

    println("Solution: ${solution}")
}

fun isDesiredRegion(coords: List<Pair<Int, Int>>, i: Int, j: Int): String {
    val distances = mutableListOf<Int>()
    coords.forEach { point ->
        val distance = abs(i - point.first) + abs(j - point.second)
        distances.add(distance)
    }

    if (distances.sum() < 10000) {
        return "#"
    } else {
        return "."
    }
}
