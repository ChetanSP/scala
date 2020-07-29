package MyPackage

object MyObject extends App {

  /*  val myList = List("abc", "def", "ghi")

  println(myList.toString().toUpperCase())

  println(myList.map(x => x.toUpperCase()))
*/
  var i = 9

  iValue { i += 1; i }

  def iValue(i: => Int) = {

    println(s"Value of i : $i" + +i)

  }

  iValue(5)

}