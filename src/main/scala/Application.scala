import scala.collection.mutable
import scala.io.Source

object Application {

  def main(args: Array[String]): Unit = {

    val fileName = args(0)
    val shardsCount = args(1).toInt

    val map = new scala.collection.mutable.HashMap[Int, Int]().withDefaultValue(0)

    for (line <- Source.fromFile(fileName).getLines) {
      val split = line.split(";")

      val routing = split.head
      val count = split.last.toInt
      val shard = Math.floorMod(Murmur3HashFunction.hash(routing), shardsCount)

      map.update(shard, map(shard) + count)
    }

    println("ShardId;Count")
    for ((k,v) <- map) {
      println(s"${k};${v}")
    }
  }
}
