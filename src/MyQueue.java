import java.util.*;
import java.io.*;

public class MyQueue<E> implements Queueable<E>
{

   private List<E> list;

   public MyQueue()
   {
      list = new LinkedList();
   }
   
/** lets the client know if the queue has any elements or is empty.
*   @return true if the queue is empty; returns false if the queue has elements stored in it.
*/ 
   public boolean isEmpty()
   {
      if(list.size() == 0)
         return true;
      return false;
   }
  
/** add x to the end of the queue.
*  precondition: queue is [a1, a2, ..., an]. 
*  postcondition: queue is [a1, a2, ..., an, x]. 
*  @param  x a non-null E object.
*/   
   public void add(E x)
   {
      list.add(x);
   } 
  
/** removes and returns the element at the front of a non-empty queue.
*  precondition: queue is [a1, a2, ..., an].  
*  postcondition: queue is [a2, ..., an]; returns a1.
* @return the value that was removed; returns null if the queue is empty.
*/
   public E remove()
   {
      if(list.size() == 0)
         return null;
      return list.remove(0);
   }
   

/** returns the element at the front of a non-empty queue.
*  precondition: queue is [a1, a2, ..., an].
*  postcondition: returns a1.  
* @return the element at the front of the queue; returns null if the queue is empty.
*/    
   public E peek()
   {
      if(list.size() == 0)
         return null;
      return list.get(0);
   }

   public String toString()
   {
      String ans = "";
      for(int i = 0; i<list.size(); i++)
      {
         ans += list.get(i) + " ";
      }
      return ans;
   }
}