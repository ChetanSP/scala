package MyAlgorithms

import scala.util.control.Breaks._

object BubbleSort extends App {

  def isSorted(inputArray: Array[Int]): Array[Int] = {

    var isSorted = false
    var lastUnsorted = inputArray.length - 1
    while (!isSorted) {
      isSorted = true
      for (i <- 0 until lastUnsorted) {
        println(inputArray(i) + " > " + inputArray(i + 1))
        //println("Now the array is: " + inputArray.mkString(","))

        if (inputArray(i) > inputArray(i + 1)) {
          swapper(inputArray, i, i + 1)
          isSorted = false
        }
      }
      lastUnsorted = lastUnsorted - 1
    }
    inputArray
  }

  def swapper(inputArray: Array[Int], a: Int, b: Int): Array[Int] = {

    val firstElement = inputArray(a)
    inputArray(a) = inputArray(b)
    inputArray(b) = firstElement

    inputArray
  }

  val aray = Array(2, 3, 7, 4, 1, 9, 6, 8, 10, 5)
  //val aray = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  val sortedArray = isSorted(aray)
  println("1 aray is: " + aray.mkString(","))

  println("2 aray is: " + sortedArray.mkString(","))

}