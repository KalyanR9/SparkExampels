import org.apache.spark.sql.SparkSession

object ExampleRepartitions extends  App {

  val spark = SparkSession.builder.appName("mapExample").master("local").getOrCreate()

  spark.conf.set("spark.eventLog.enabled", "true")
  spark.conf.set("spark.eventLog.dir", "file:///C:/spark/logs")

  val people = List(
    (10, "blue"),
    (13, "red"),
    (15, "blue"),
    (99, "red"),
    (67, "blue")
  )

  import spark.implicits._

  val peopleDf = people.toDF("age", "color")

  //val colorDf = peopleDf.repartition(10,$"color")

  val colorDff = peopleDf.coalesce(2)

  //colorDf.show()
  colorDff.show()

  val squared1 = (s: Long) => {
    s * s
  }
  spark.udf.register("square", squared1)
  spark.range(1, 5).createOrReplaceTempView("test")
  val results = spark.sql("select id, square(id) as id_squared from test")
  results.show()

  import org.apache.spark.sql.functions.{col, udf}
  val squared = udf((s: Long) => s * s)
  spark.range(1, 5).select(squared(col("id")) as "id_squared").show()

  System.in.read
  spark.stop()
}
