package MyAlgorithms

import scala.util.control.Breaks._

object BinarySearchArray extends App {

  val aray = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  val searchKey = 9

  var low = aray(0)
  var high = aray(aray.length - 1)
  var mid = (low + high) / 2

  println(s"Low: $low High: $high Mid: $mid")

  if (searchKey < low || searchKey > high) {
    println("Element is not present in the array.")
  } else {
    val result = BinSearch(low, mid, high, searchKey)
  }

  def BinSearch(low: Int, mid: Int, high: Int, searchKey: Int): Boolean = {

    if (mid == searchKey) {
      println("Search element found..!")
      println(s"The location of the element is: ${aray.indexOf(mid)}")
    } else if (searchKey < mid) {
      val newHigh = mid
      val newMid = (low + newHigh) / 2
      BinSearch(low, newMid, newHigh, searchKey)
    } else if (searchKey > mid) {
      val newLow = mid
      val newMid = (newLow + high) / 2
      BinSearch(newLow, newMid, high, searchKey)
    }
    false
  }
}