package InterviewPrograms

import scala.annotation.tailrec


object Fibonacci extends App {

  def fibo(n: Int): Int = {

    @tailrec
    def fibo3(n: Int, a: Int, b: Int): Int = n match {
      case 0 => a
      case _ => fibo3(n-1, b, a + b)
    }

    return fibo3(n, 0, 1)
  }

  println(s"The Fibonacci series of 1 to 15 is: ")
  for (i <- 0 to 15)
    print(s" " +fibo(i))

}
