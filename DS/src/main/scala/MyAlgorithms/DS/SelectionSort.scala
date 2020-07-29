package main.scala.MyAlgorithms.DS

object SelectionSort extends App {
  val aray = Array(2, 3, 7, 4, 1, 9, 6, 8, 10, 5)
  println("1 aray is: " + aray.mkString(","))

  val sortedArray = isSorted(aray)
  println("2 aray is: " + sortedArray.mkString(","))

  def isSorted(inputArray: Array[Int]): Array[Int] = {
    var isSorted = false
    var myMaxValue = inputArray(0)
    var myMaxIndex = 0
    var swapIndex = 0
    for (i <- 0 until inputArray.length - 1) {
      myMaxValue = inputArray(0)
      for (j <- 1 until (inputArray.length - 1 - i)) {
        println(s"New max : $myMaxValue")
        if (inputArray(j) > myMaxValue) {
          println(inputArray(j) + " > " + myMaxValue)
          myMaxValue = inputArray(j)
          myMaxIndex = j
        }
        swapIndex = (inputArray.length - 1 - i)
      }
      println("Array so far is: " + inputArray.mkString(","))
      println(s"Calling Swapper for value:  ${inputArray(myMaxIndex)} , ${inputArray(swapIndex)}")
      //println(s"Calling Swapper for index: ${(myMaxIndex)} , ${(swapIndex)}")
      swapper(inputArray, swapIndex, myMaxIndex)
      println("Array so far is: " + inputArray.mkString(","))
      println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
    }

    inputArray
  }

  def swapper(inputArray: Array[Int], a: Int, b: Int): Array[Int] = {

    val firstElement = inputArray(a)
    inputArray(a) = inputArray(b)
    inputArray(b) = firstElement

    inputArray
  }
}