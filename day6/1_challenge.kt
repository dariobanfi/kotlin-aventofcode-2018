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

    val infiniteIds = mutableListOf<String>()

    for (i in 0..matrix.size - 1) {
        for (j in 0..matrix[i].size - 1) {
            if (matrix[i][j] == ".") {
                matrix[i][j] = computeClosestPoint(matrix, coords, i, j)
                if (i == 0 || j ==0 || i == highestX || j == highestY) {
                    infiniteIds.add(matrix[i][j])
                }
            }
        }
    }

    val uniqueInfiniteIds = infiniteIds.distinct()

    val solution = matrix
            .flatten()
            .groupingBy { it }
            .eachCount()
            .filter { !uniqueInfiniteIds.contains(it.key) }
            .maxBy { it.value }

    println("Solution: ${solution!!.value}")
}

fun computeClosestPoint(matrix: Array<Array<String>>, coords: List<Pair<Int, Int>>, i: Int, j: Int): String {
    val distances = mutableListOf<Pair<String, Int>>()
    coords.forEachIndexed { index, point ->
        val distance = abs(i - point.first) + abs(j - point.second)
        distances.add(Pair(index.toString(), distance))
    }

    val minDistance = distances.minBy { it.second }
    distances.remove(minDistance)
    val secondMinDistance = distances.minBy { it.second }

    if (secondMinDistance?.second == minDistance?.second) {
        return "."
    } else {
        return minDistance!!.first
    }
}
