package MyPackage

/*class myClass(myArg: Int) {

  private var myVal = myArg
  def printMyVal() = {
    println(s"myVal value : $myVal")
  }
}
*/
object MyObject extends App {

  val poly = new Triangle(2, 5)
  println(s"Test triangle area: " + poly.area(2, 5))
  var mul = (x: Int, y: Int) => x * y

  println(mul(3, 4))

}

abstract class Polygon {
  def area(length: Double, height: Double): Double = {
    println("def area : nothing is happening here.")
    return 0

  }

  def info(msg: String) = {
    println("Hello World.!")
  }

}


class Triangle(var length: Double, val height: Double) extends Polygon {

  override def info(msg: String) {
    println("nothing is happening here.")
  }
}

class Rectangle(var length: Double, val height: Double) extends Polygon {
  override def area(length: Double, height: Double): Double = {

    return (length + height)
  }

  override def info(msg: String) {
    println("nothing is happening here.")
  }

  def info(msg: String, msg2: String): Unit = {
    println(x = "Hai")
  }

}