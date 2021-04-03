package MyAlgorithms

object MergeSort extends App {

  // recursively merge 2 sorted lists
  def merger(left: List[Int], right: List[Int]): List[Int] =
    (left, right) match {
      case (left, Nil) => left
      case (Nil, right) => right
      case (leftHead :: leftTail, rightHead :: rightTail) =>
        if (leftHead < rightHead) leftHead :: merger(leftTail, right)
        else rightHead :: merger(left, rightTail)
    }

  def splitter(list: List[Int]): List[Int] = {
    val n = list.length / 2
    if (n == 0) list // i.e. if list is empty or single value, no sorting needed
    else {
      val (left, right) = list.splitAt(n)
      merger(splitter(left), splitter(right))
    }
  }

  println(s" Sorted data is: " + splitter(List(33, 44, 22, -10, 99))) //> res0: List[Int] = List(-10, 22, 33, 44, 99)

}