import org.apache.spark.mllib.linalg.distributed.{RowMatrix, CoordinateMatrix, MatrixEntry}
import org.apache.spark.mllib.recommendation.Rating

def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
}


val data = sc.textFile("/tmp/BenElechi1.mtx")
val entries = data.map(_.split(' ') match { case Array(x, y, value) =>
    MatrixEntry(x.toLong, y.toLong, value.toDouble)
})

val data2 = sc.textFile("/tmp/BenElechi1_b.mtx")
val entries2 = data2.map(_.split(' ') match { case Array(x, y, value) =>
    MatrixEntry(x.toLong, y.toLong, value.toDouble)
})


val a2 = new CoordinateMatrix(entries)
val a = a2.toBlockMatrix
val b2 = new CoordinateMatrix(entries2)
val b = b2.toBlockMatrix


time { val sim = a.multiply(b) }
