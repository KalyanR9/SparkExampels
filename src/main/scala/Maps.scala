import breeze.linalg.sum
import org.apache.spark.sql.SparkSession

object Maps extends App{
  /*val spark = SparkSession.builder.appName("mapExample").master("local").getOrCreate()
  val df =   spark.sparkContext.parallelize(Seq(1,2,3,4,5))*/

  val oneTwo = List(1, 2)
  val threeFour = List(3, 4)
  val oneTwoThreeFour = oneTwo ::: threeFour
  println(oneTwo + " and " + threeFour + " were not mutated.")
  println("Thus, " + oneTwoThreeFour + " is a new list.")

  val twoThree = List(2, 3)
  val oneTwoThree = 1 :: twoThree
  println(oneTwoThree)

  var fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
  fruit = "apples" :: "oranges" :: "pears" :: Nil
  println(fruit)

}
