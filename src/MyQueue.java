

import java.util.*;
public class MyQueue {
  LinkedList<NodeBook> t;
  MyQueue() {
    t = new LinkedList<NodeBook>();  
  }
  boolean isEmpty() {
    return(t.isEmpty());  
  }
  void clear() {
    t.clear();  
  }
  void enqueue(NodeBook p) {
     t.add(p); 
  }
  NodeBook dequeue() {
    if(isEmpty()) return(null);
    return(t.removeFirst());
  }  
  NodeBook front() {
    if(isEmpty()) return(null);
    return(t.getFirst());
  }  
}
