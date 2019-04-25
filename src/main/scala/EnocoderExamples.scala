
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.encoders
import org.apache.spark.sql.catalyst.encoders.{ExpressionEncoder, RowEncoder}
import org.apache.spark.sql.types.{LongType, StructField, StructType}

object EnocoderExamples extends App {
  case class University(name: String, numStudents: Long, yearFounded: Long)
  implicit val peopleEncoder = org.apache.spark.sql.Encoders.kryo[University]
  val spark = SparkSession.builder.appName("enocoderExamples").master("local").getOrCreate()

  val inputDF = spark.read.json("resources/student.json")

  import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
  import org.apache.spark.sql.catalyst.encoders.RowEncoder
  import org.apache.spark.sql.types.DataTypes

  var structType = new StructType()
  structType = structType.add("id1", DataTypes.LongType, false)
  structType = structType.add("id2", DataTypes.LongType, false)

  val schema = StructType(structType)
  val encoder = RowEncoder.apply(structType)
  var recordCounter = 0

  val outputDF = inputDF.mapPartitions(itr => {
    itr.map(row => {
      recordCounter=recordCounter+1
      row
    })
  })(RowEncoder(inputDF.schema))

  //inputDF.map(itr=>itr)(RowEncoder(inputDF.schema)).show()
  println(recordCounter)
}
