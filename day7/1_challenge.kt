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

    println(graph.values.sortedBy { it.id }.map { "${it.id}: ${it.edges}\n"})


    // Find first task which no node depends on
    val firstTasks = mutableListOf<String>()
    for (key in graph.keys) {
        val first = graph.values.find { it.edges.contains(key) }?.id
        if (first == null) {
            firstTasks.add(key)
        } else {
            continue
        }
    }

    println("firstTasks: $firstTasks")

    val visitQueue = PriorityQueue<String>();
    val sequence = mutableListOf<String>()

    visitQueue.addAll(firstTasks);


    while(!visitQueue.isEmpty()) {
        println("$visitQueue - next: ${visitQueue.peek()}")
        val visiting = visitQueue.poll();
        sequence.add(visiting)
        val node = graph[visiting]
        for (edge in node!!.edges) {
            if (!sequence.contains(edge) && !visitQueue.contains(edge)) {
                if (canBeAdded(graph, edge, sequence)) {
                    visitQueue.add(edge)
                }
            }
        }
    }

    println("Solution is ${sequence.joinToString("")}")

}

fun canBeAdded(graph: MutableMap<String, Node>, edge: String, visited: MutableList<String>): Boolean {

    val unvisitedNodes = graph.values.filter { !visited.contains(it.id) }

    for (node in unvisitedNodes) {
        if (node.edges.contains(edge)) {
            return false;
        }
    }

    return true;

}

data class Node (
        val id: String,
        var edges: MutableList<String>
)
