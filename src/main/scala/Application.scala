import scala.io.Source

object Application {

  def main(args: Array[String]): Unit = {

    val fileName = args(0)
    val shardsCount = args(1).toInt

    for (line <- Source.fromFile(fileName).getLines) {
      println(Math.floorMod(Murmur3HashFunction.hash(line), shardsCount))
    }
  }
}
