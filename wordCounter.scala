package main

import org.apache.spark.SparkContext
import org.apache.sparkSparkConf

object WordCount {
  def main(args: Array[String]){
  
    val conf = new SparkConf().setAppName("Word Counter")
    val sc = new SparkContext(conf)

    val textFile = sc.textFile("/home/marden/spark_training/sample.md")
    val tokenizedFileData = textFile.flatMap(line=>line.split(" ")) 
    val countPrep = tokenizedFileData.map(word=>(word,1))
    val counts = countPrep.reduceByKey((accumValue, newValue) => accumValue + newValue)
    val sortedCounts = counts.sortBy(kvPair._2, false)
    sortedCounts.saveAsTextFile("/home/marden/spark_training/WordCount")
    
  }
}
