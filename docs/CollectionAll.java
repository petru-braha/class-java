import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Set;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class CollectionAll {

  public static void main(String args[]) {

    List interface0;
    ArrayList implementation11;
    Vector implementation1;
    Stack implementation2;


    Queue interface1;
    PriorityQueue implementation5;

    Deque interface12;
    ArrayDeque implementation3;
    PriorityBlockingQueue implementation4;


    Set interface2;
    HashSet implementation84;
    
    SortedSet interface3;
    TreeSet implementation61; // has NavigableMap
    // LinkedHashSet
    
    Dictionary implementation7;
    NavigableSet interface65;


    Map interface4;
    Hashtable implementation17;
    HashMap implementation116;
    // LinkedHashMap

    SortedMap interface5;
    TreeMap implementation44;

    NavigableMap interface6;


    LinkedList implementation0;
    LinkedBlockingDeque implementation18;
    LinkedBlockingQueue implementation19;
    LinkedHashSet implementation21;
    LinkedHashMap implementation20;
    LinkedTransferQueue implementation22;


    ConcurrentMap implementation13;
    ConcurrentHashMap implementation9;
    ConcurrentNavigableMap implementation14;
    ConcurrentLinkedQueue implementation12;
    ConcurrentLinkedDeque implementation10;
    ConcurrentSkipListMap implementation15;
    ConcurrentSkipListSet implementation16;
  }
}
