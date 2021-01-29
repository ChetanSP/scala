package MyPackage

object Flipper extends App {

  val flippy = { (char: Char) =>
    if (char == '0') '1'
    else '0'
  }

  def flipCounter(myStr: String, TheExpectedCharacter: Char): Int = {
    var expectedCharacter: Char = TheExpectedCharacter
    var numberOfFlipsRequired: Int = 0
    var alternateString = ""
    for (i <- 0 to myStr.length - 1) {
      if (myStr(i) != expectedCharacter)
        numberOfFlipsRequired = numberOfFlipsRequired + 1
      expectedCharacter = flippy(expectedCharacter)
      alternateString = alternateString + expectedCharacter
    }
    println(s"alternateString : $alternateString")
    return numberOfFlipsRequired
  }

  val myStr = "0001010111"
  println(s"myStr is : $myStr")
  val minFlipsRequired = (flipCounter(myStr, '1')).min(flipCounter(myStr, '0'))
  println(s"minFlipsRequired is: $minFlipsRequired")
}
