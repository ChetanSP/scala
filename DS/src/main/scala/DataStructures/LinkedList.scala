package DataStructures

trait LinkedList[A] {
  def fetch(index: Int): A

  def insertAfter(index: Int, data: A): Unit

  def update(index: Int, data: A): Unit

  def deleteNodeAtGivenPosition(index: Int): Unit

  def deleteGivenData(DataToBeDeleted: A): Unit
}


class myLinkedList[A] extends LinkedList[A] {

  private class Node(var data: A, var next: Node)

  private var head: Node = null

  def fetch(index: Int): A = {
    require(index >= 0)
    var rover = head
    for (i <- 0 until index)
      rover = rover.next
    rover.data
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

  def deleteNodeAtGivenPosition(position: Int): Unit = {

    if (head.next != null) {
      var temp: Node = head

      // If head needs to be removed
      if (position == 0) {
        head = temp.next
        return
      }

      // Find previous node of the node to be deleted
      for (i <- 0 until (position - 1) if temp != null) {
        temp = temp.next
        if (temp == null || temp.next == null)
          return

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        val next: Node = temp.next.next
        temp.next = next
      }
    }
  }

  def deleteGivenData(DataToBeDeleted: A): Unit = {
    ???
  }
}