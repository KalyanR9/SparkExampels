import org.apache.spark.sql.SparkSession
import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.ReadConf
import org.apache.spark.sql.types.{DecimalType, StringType}

object CassandraTest extends App {
  case class WordCount(word: String, count: Long)

  val spark = SparkSession.builder.appName("CassandraTest").master("local")
    .config("spark.cassandra.connection.host", "localhost")
    .getOrCreate()

  //val studentDF = spark.read.json("resources/student.json")
  /*val collection = spark.sparkContext.parallelize(Seq(WordCount("cat", 30), WordCount("fox", 40)))
  collection.saveToCassandra("test", "words", SomeColumns("word", "count"))*/

  val df = spark
    .read.format("org.apache.spark.sql.cassandra")
    .options(Map("keyspace" -> "test", "table" -> "words"))
    .load()

  df.show()


/*   import org.apache.spark.sql.cassandra._
  val dff = spark
    .read
    .cassandraFormat("words", "test")
    .options(ReadConf.SplitSizeInMBParam.option(32))
    .load()
  dff.printSchema()
  dff.show()
 val createDDL = """CREATE TEMPORARY VIEW words2
     USING org.apache.spark.sql.cassandra
     OPTIONS (
     table "words",
     keyspace "test",
     cluster "Test Cluster",
     pushdown "true")"""
  spark.sql(createDDL) // Creates Catalog Entry registering an existing Cassandra Table
  spark.sql("SELECT * FROM words2").show
  spark.sql("SELECT * FROM test.words2 WHERE word = 'fox'").show*/

}
