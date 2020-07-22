package main.scala.MyAlgorithms.DS

object SelectionSort extends App {
  val aray = Array(2, 3, 7, 4, 1, 9, 6, 8, 10, 5)

  val sortedArray = isSorted(aray)
  println("1 aray is: " + aray.mkString(","))

  println("2 aray is: " + sortedArray.mkString(","))

  def isSorted(inputArray: Array[Int]): Array[Int] = {
    var isSorted = false
    var mymax = inputArray(0)

    while (!isSorted) {
      isSorted = true
      var swapValue = 0
      for (i <- 0 until inputArray.length - 1) {
        swapValue = inputArray(i)
        if (inputArray(i) > mymax) {
          println(inputArray(i) + " > " + mymax)
          isSorted = false
          mymax = inputArray(i)

        }
        println(s"i: $i and (inputArray.length - 2) = ${inputArray.length - 2}")
        if (i == inputArray.length - 2) {
          println("Calling Swapper")
          println(s"mymax: $mymax  swapValue : $swapValue")
          swapper(inputArray, mymax, swapValue)
        }
      }

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