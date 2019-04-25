import com.fasterxml.jackson.core.JsonParseException
import org.apache.spark.SparkException
import org.apache.spark.sql._
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.catalyst.encoders.{ExpressionEncoder, RowEncoder}
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

import scala.collection.mutable

object mapTest {
  case class University(name: String, numStudents: Long, yearFounded: Long)
  case class Dewiki(search: String, name: String, link: String, clicktimes: String)
  def main(args: Array[String]) = {
    val spark = SparkSession.builder.appName("mapExample").master("local").getOrCreate()
    import spark.implicits._
    import org.apache.spark.sql.functions._
   implicit val peopleEncoder = org.apache.spark.sql.Encoders.kryo[Dewiki]
    try{
      val studentDF = spark.read.json("resources/student.json").as[University]

     /* val sampleDF = spark.read.textFile("C:\\Users\\kalyanr\\Downloads\\clickstream-dewiki-2019-01.tsv")

      val partdata = sampleDF.map(p => p.split("\t"))
      val processedDataset: Dataset[Dewiki] =  partdata.map(line=>{
        Dewiki(line(0), line(1),line(2),line(3).toLong)
      })

      processedDataset.toDF.show(4)*/

      import spark.sqlContext.implicits._

      val dff = spark.read.option("delimeter","\t")
        .option("inferSchema", "true")
        .csv("C:\\Users\\kalyanr\\Downloads\\clickstream-dewiki-2019-01.tsv")
        .sample(.01)

      val prdt:Dataset[Dewiki] = dff.map{line => Dewiki(line(0).toString, line(1).toString, line(2).toString, line(3).toString)}
      prdt.show(5)


      /*val df = x.filter($"_corrupt_record".isNotNull)select("_corrupt_record")
      df.select(expr("(split(_corrupt_record, ','))[0]").cast("string").as("name"),
        expr("(split(_corrupt_record, ','))[1]").cast("string").as("yearFounded"),
        expr("(split(_corrupt_record, ','))[2]").cast("string").as("numStudents"))
        .show*/

      val schema = StructType(StructField("id", LongType, nullable = false)
                    ::StructField("name", StringType, nullable = false)
                          ::StructField("name", LongType, nullable = false)::Nil)
      /*val stringEncoder = ExpressionEncoder[String]
      val row  : InternalRow = stringEncoder.toRow("\"age\":30 ,\"name\":\"Michael\"")*/
    }
    catch{
      case e: SparkException =>{
        println("currupt records")
        }
      case ex : JsonParseException=>{
        println("currupt records")
      }
    }
    //x.where($"name")
  }

  def getRow(x : String) : Row={
    val columnArray = new Array[String](4)
    columnArray(0)=x.substring(0,3)
    columnArray(1)=x.substring(3,13)
    columnArray(2)=x.substring(13,18)
    columnArray(3)=x.substring(18,22)
    Row.fromSeq(columnArray)
  }
}
