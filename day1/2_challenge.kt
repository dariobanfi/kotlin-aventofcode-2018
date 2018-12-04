package day1

import java.io.File

fun main(args : Array<String>) {
    val numbers: List<Int> = File("day1/input.txt").readLines().map { it.toInt() }

    var frequency = 0

    val frequencies = mutableSetOf<Int>()

    var foundSolution: Int? = null;

    var counter = 0

    while (foundSolution == null) {
        val number = numbers[counter]
        frequency += number

        if (frequencies.contains(frequency)) {
            foundSolution = frequency
            break;
        } else {
            frequencies.add(frequency)
        }

        counter = ++counter % numbers.size
    }


    println("Solution is: $foundSolution")
}
