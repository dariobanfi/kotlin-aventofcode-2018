package day7

import java.io.File
import java.util.*

fun main(args : Array<String>) {
    val input = File("day7/input.txt").readLines()

    val graph = mutableMapOf<String, Node>()

    input.forEach { line ->
        val first = line.split(" ")[1]
        val second = line.split(" ")[7]

        graph.putIfAbsent(first, Node(first, mutableListOf()))
        graph.putIfAbsent(second, Node(second, mutableListOf()))

        graph[first]?.edges?.add(second)

    }

    println(graph.map { "${it.value.id} ${it.value.edges}"})


    // Find first task which no node depends on
    var firstTask = ""
    for (key in graph.keys) {
        val first = graph.values.find { it.edges.contains(key) }?.id
        if (first == null) {
            firstTask = key
        } else {
            continue
        }
    }

    val visitQueue = PriorityQueue<String>();
    val sequence = mutableListOf<String>()

    visitQueue.add(firstTask);


    while(!visitQueue.isEmpty()) {
        println("$visitQueue - next: ${visitQueue.peek()}")
        val visiting = visitQueue.poll();
        sequence.add(visiting)
        val node = graph[visiting]
        for (edge in node!!.edges) {
            if (!sequence.contains(edge) && !visitQueue.contains(edge)) {
                // Check if any graph in the queue has dependencies
                val dependencies = visitQueue.map { graph[it]?.edges?.contains(edge) }
                if (!dependencies.contains(true)) {
                    visitQueue.add(edge)
                }
            }
        }
    }

    println("Solution is ${sequence.joinToString("")}")



}

data class Node (
        val id: String,
        var edges: MutableList<String>
)
