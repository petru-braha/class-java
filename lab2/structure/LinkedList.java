package lab2.structure;

// first in first out implementation
public class LinkedList<T> {

  public class Node {

    public T data;
    public Node next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }

  }

  private Node head, tail;
  private int count;

  public LinkedList() {
    this.head = this.tail = null;
    count = 0;
  }

  public void insertTail(T data) {

    count++;
    Node newNode = new Node(data);
    if (head == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
  }

  public void removeHead() {

    if (null == head)
      return;
    count--;
    head = head.next;
  }

  public LinkedList<T>.Node getH() {
    return head;
  }

  public LinkedList<T>.Node getT() {
    return tail;
  }

  public int getN() {
    return count;
  }
}
