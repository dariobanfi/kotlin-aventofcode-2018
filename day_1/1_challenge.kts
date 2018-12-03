import java.io.File
import java.io.InputStream

val fileLines: List<String> = File("./input.txt").readLines()
val result = fileLines
        .map{
            it.toInt()
        }
        .reduce { acc, line ->
            acc + line
        }

println("Solution is: $result")
