import mapTest.Dewiki
import org.apache.spark.sql._

object SparkExample2 extends App {

  case class Dewiki(search: String, name: String, link: String, clicktimes: Int)

  //implicit val peopleEncoder = Encoders.kryo[Dewiki]
  implicit val encoder = Encoders.product[Dewiki]
  val spark = SparkSession.builder.appName("mapExample").master("local").getOrCreate()

  val dff = spark.read.format("csv")
    .option("sep", "\t")
    .option("inferSchema", "true")
    .option("header", "true")
    .load("C:\\Users\\kalyanr\\Downloads\\clickstream-dewiki-2019-01.tsv")

  val df  = dff.toDF("search","name","link","clicktimes").as[Dewiki]

  df.write.mode(SaveMode.Overwrite).partitionBy("clicktimes").format("json").
    save("C:/Users/kalyanr/Desktop/example.json")
}


