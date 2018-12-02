import java.io.File
import java.io.InputStream

val fileLines: List<String> = File("./1_input.txt").readLines()
val result = fileLines
        .map{
            it.toInt()
        }
        .reduce { acc, line ->
            acc + line
        }

println("Solution is: $result")
