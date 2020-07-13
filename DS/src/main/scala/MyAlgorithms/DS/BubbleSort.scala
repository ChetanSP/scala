package MyAlgorithms.DS
import scala.util.control.Breaks._

object BubbleSort extends App {

  val aray = Array(2, 3, 7, 4, 1, 9, 6, 8, 10, 5)

  //val aray = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  //println(s"The input array is sorted? : " +  isSorted(aray))

  def isSorted(inputArray: Array[Int]): Boolean = {
    var bool = false
    breakable {

      for (i <- 0 until inputArray.length - 1) {
        if (inputArray(i) < inputArray(i + 1)) { bool = true }
        else {
          bool = false

          break
        }
      }
    }
    bool
  }

  bubbleSort(aray)

  def bubbleSort(inputArray: Array[Int]): Boolean = {
    for (i <- 0 until inputArray.length - 1) {
      println(s"************Outer Loop: $i****************")
      breakable {
        for (j <- 0 to (inputArray.length - 1 - i)) {
          //breakable {
          println(s"************Inner Loop: $j****************")
          println("OutputArray is: " + inputArray.mkString(","))
          if (inputArray(j) > inputArray(j + 1)) {
            println(s"Value of ${inputArray(j)} > ${inputArray(j + 1)}. Elements will be swapped.")
            swapper(inputArray, j, j + 1)
          } /*else {
            println(s"Value of ${inputArray(i)} < ${inputArray(i + 1)}")
            break
          }*/
        }
      }
    }
    println("OutputArray is: " + inputArray.mkString(","))
    true
  }

  def swapper(inputArray: Array[Int], a: Int, b: Int): Array[Int] = {
    //println("1 aray is: " + inputArray.mkString(","))

    val firstElement = inputArray(a)
    val secondElement = inputArray(b)

    inputArray(a) = secondElement
    inputArray(b) = firstElement
    //println("2 aray is: " + inputArray.mkString(","))

    inputArray
  }

  //swapper(aray, 0, 1)

}