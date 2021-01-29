package MyPackage

object Flipper extends App {

  def newFlippy(ch: Char): Char = {
    if (ch == '0')
      return '1'
    else
      return '0'
  }

  val flippy = { (char: Char) =>
    if (char == '0') '1'
    else '0'
  }

  def flipCounter(myStr: String, TheExpectedCharacter: Char): Int = {
    var expectedCharacter: Char = TheExpectedCharacter
    var NumberOfFlipsRequired: Int = 0
    var myNewStr = ""
    for (i <- 0 to myStr.length - 1) {
      if (myStr(i) != expectedCharacter)
        NumberOfFlipsRequired = NumberOfFlipsRequired + 1
      expectedCharacter = newFlippy(expectedCharacter)
      myNewStr = myNewStr + expectedCharacter
    }
    println(s"myNewStr : $myNewStr")
    return NumberOfFlipsRequired
  }

  val myStr = "0001010111"
  println(s"myStr is : $myStr")
  val minFlipsRequired = (flipCounter(myStr, '1')).min(flipCounter(myStr, '0'))
  println(s"minFlipsRequired is: $minFlipsRequired")
}
