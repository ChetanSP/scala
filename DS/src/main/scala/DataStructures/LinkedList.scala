package DataStructures

trait LinkedList[A] {
  def fetch(index: Int): A

  def insertAfter(index: Int, data: A): Unit

  def update(index: Int, data: A): Unit

  def delete(index: Int): Unit
}


class myLinkedList[A] extends LinkedList[A] {

  private class Node(var data: A, var next: Node)

  private var head: Node = null

  def fetch(index: Int): A = {
    require(index >= 0)
    var rover = head
    for (i <- 0 until index)
      rover = rover.next
    return rover.data
  }

  def update(index: Int, data: A): Unit = {
    require(index >= 0)
    var rover = head
    for (i <- 0 until index)
      rover = rover.next
    rover.data = data
  }

  def insertAfter(index: Int, data: A): Unit = {
    require(index >= 0)
    if (index == 0) {
      head = new Node(data, head)
    }
    else {
      var rover = head
      for (i <- 0 until index)
        rover = rover.next
      var temp = rover.next
      rover.next = new Node(data, temp)
    }
  }

  def delete(index: Int): Unit = {
    require(index >= 0)
    var rover = head
    for (i <- 0 until index - 1)
      rover = rover.next
    rover.next = index + 1
    println("Data of " )
  }


}