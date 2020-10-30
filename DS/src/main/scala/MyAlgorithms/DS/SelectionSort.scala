package main.scala.MyAlgorithms.DS

object SelectionSort extends App {
  val aray = Array(2, 3, 7, 4, 1, 9, 6, 8, 10, 5)
  //val aray = Array(64, 25, 12, 22, 11)

  println("1 aray is: " + aray.mkString(","))

  val sortedArray = isSorted(aray)
  println("2 aray is: " + sortedArray.mkString(","))

  def isSorted(NewinputArray: Array[Int]): Array[Int] = {

    var inputArray = NewinputArray
    for (i <- 0 to inputArray.length - 1) {
      var MyMin = inputArray(i)
      var MyMinIndex = i
      for (j <- i + 1 to inputArray.length - 1) {
        if (inputArray(j) < inputArray(i)) {
          println(s"${inputArray(j)} < ${inputArray(i)}")
          println(s"OLD :MyMin :$MyMin and  MyMinIndex: $MyMinIndex")
          MyMin = inputArray(j)
          MyMinIndex = j
          println(s"NEW :MyMin :$MyMin and  MyMinIndex: $MyMinIndex")
        }
        if (j == inputArray.length - 1) {
          println(s"***************MyMinIndex: $MyMinIndex and i : $i****************")
          println(s"###########${inputArray(MyMinIndex)} < ${inputArray(i)}###########")
          val swappedArray = swapper(inputArray, MyMinIndex, i)
          println(s"***************swappedArray: ${swappedArray.mkString(",")} $i****************")
          inputArray = swappedArray
        }
      }
    }
    inputArray
  }

  def swapper(inputArray: Array[Int], IndexI: Int, IndexJ: Int): Array[Int] = {

    val firstElement = inputArray(IndexI)
    inputArray(IndexI) = inputArray(IndexJ)
    inputArray(IndexJ) = firstElement
    println(s"${inputArray.mkString(",")}")

    inputArray
  }
}